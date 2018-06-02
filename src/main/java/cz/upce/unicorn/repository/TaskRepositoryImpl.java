package cz.upce.unicorn.repository;

import cz.upce.unicorn.repository.entity.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TaskRepositoryImpl extends AbstractRepositoryImpl<Task> implements TaskRepository {

    protected Class<Task> getClazz() {
        return Task.class;
    }

    @Override
    public List<Task> findByStatus(Boolean completed) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(getClazz());

        Root<Task> root = cq.from(getClazz());
        cq.select(root).where(cb.equal(root.get("completed"),completed));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
