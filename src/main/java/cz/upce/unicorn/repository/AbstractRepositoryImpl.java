package cz.upce.unicorn.repository;


import cz.upce.unicorn.repository.entity.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public abstract class AbstractRepositoryImpl<T extends Identifiable> implements AbstractRepository<T> {

    protected abstract Class<T> getClazz();

    @PersistenceContext
    private EntityManager entityManager;

    public final EntityManager getEntityManager() {
        return entityManager;
    }

    public T findById(Integer id) {
        return entityManager.find(getClazz(), id);
    }

    public T save(T item) {
        return entityManager.merge(item);
    }

    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getClazz());

        Root<T> root = cq.from(getClazz());

        List<Predicate> predicates = new ArrayList<Predicate>();
        cq.select(root).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(cq).getResultList();
    }

    public void delete(Integer id) {
        T entity = entityManager.find(getClazz(), id);
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }



}
