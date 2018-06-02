/*jshint eqeqeq:false */
(function (window) {
    'use strict';

    /**
     * Creates a new client side storage object and will create an empty
     * collection if no collection already exists.
     *
     * @param {string} name The name of our DB we want to use
     * @param {function} callback Our fake DB uses callbacks because in
     * real life you probably would be making AJAX calls
     */
    function Store(name, callback) {
        callback = callback || function () {
        };

        this._dbName = name;

        if (!localStorage[name]) {
            var data = {
                todos: []
            };

            localStorage[name] = JSON.stringify(data);
        }


        var xmlhttp = new XMLHttpRequest();
        var url = "http://localhost:8080/tasks/";

        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var myArr = JSON.parse(this.responseText);
                callback.call(this, (myArr));
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    }

    /**
     * Finds items based on a query given as a JS object
     *
     * @param {object} query The query to match against (i.e. {foo: 'bar'})
     * @param {function} callback     The callback to fire when the query has
     * completed running
     *
     * @example
     * db.find({foo: 'bar', hello: 'world'}, function (data) {
     *	 // data will return any items that have foo: bar and
     *	 // hello: world in their properties
     * });
     */
    Store.prototype.find = function (query, callback) {
        if (!callback) {
            return;
        }


        var xmlhttp = new XMLHttpRequest();
        var url = "http://localhost:8080/tasks/" + query.id;

        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var myArr = (this.responseText);
                callback.call(this, JSON.parse(myArr));
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    };

    /**
     * Will retrieve all data from the collection
     *
     * @param {function} callback The callback to fire upon retrieving data
     */
    Store.prototype.findAll = function (callback) {
        callback = callback || function () {
        };

        var xmlhttp = new XMLHttpRequest();
        var url = "http://localhost:8080/tasks/";

        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var myArr = JSON.parse(this.responseText);
                callback.call(this, (myArr));
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();

    };

    /**
     * Will save the given data to the DB. If no item exists it will create a new
     * item, otherwise it'll simply update an existing item's properties
     *
     * @param {object} updateData The data to save back into the DB
     * @param {function} callback The callback to fire after saving
     * @param {number} id An optional param to enter an ID of an item to update
     */
    Store.prototype.save = function (updateData, callback, id) {
        var data = JSON.parse(localStorage[this._dbName]);
        var todos = data.todos;

        callback = callback || function () {
        };

        if (id) { //update

            updateData.id = id;
            var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
            xmlhttp.open("POST", "http://localhost:8080/tasks/");
            xmlhttp.setRequestHeader("Content-Type", "application/json");
            xmlhttp.send(JSON.stringify(updateData));


            callback.call(this, todos);
        } else { //new

            var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
            xmlhttp.open("POST", "http://localhost:8080/tasks/");
            xmlhttp.setRequestHeader("Content-Type", "application/json");
            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var myArr = JSON.parse(this.responseText);
                    callback.call(this, (myArr));
                }
            };
            xmlhttp.send(JSON.stringify(updateData));

        }
    };

    /**
     * Will remove an item from the Store based on its ID
     *
     * @param {number} id The ID of the item you want to remove
     * @param {function} callback The callback to fire after saving
     */
    Store.prototype.remove = function (id, callback) {
        var xmlhttp = new XMLHttpRequest();
        var url = "http://localhost:8080/tasks/" + id;
        xmlhttp.open("DELETE", url, true);
        xmlhttp.send();
        // localStorage[this._dbName] = JSON.stringify(data);

        var xmlhttp = new XMLHttpRequest();
        var url = "http://localhost:8080/tasks/";

        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var myArr = JSON.parse(this.responseText);
                callback.call(this, (myArr));
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();


    };

    /**
     * Will drop all storage and start fresh
     *
     * @param {function} callback The callback to fire after dropping the data
     */
    Store.prototype.drop = function (callback) {
        var data = {todos: []};
        localStorage[this._dbName] = JSON.stringify(data);
        callback.call(this, data.todos);
    };

    // Export to window
    window.app = window.app || {};
    window.app.Store = Store;
})(window);