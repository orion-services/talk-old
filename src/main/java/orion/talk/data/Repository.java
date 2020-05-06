/**
 * @License
 * Copyright 2020 Orion Services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package orion.talk.data;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class Repository<T> {

    @PersistenceContext(name = "OrionTalkServiceDS")
    private EntityManager em;

    public T create(final T obj) {
        this.em.persist(obj);
        return obj;
    }

    public List<T> read() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        final Root<T> root = criteria.from(this.genericClass());
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }

    public void update(T obj) {
        this.em.merge(obj);
    }

    public void delete(Long id) {
        T obj = em.find(this.genericClass(), id);
        this.em.remove(obj);
    }

    public T find(Long id) {
        return em.find(this.genericClass(), id);
    }

    public T find(String column, String value) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        Root<T> root = criteria.from(this.genericClass());
        criteria.select(root).where(builder.equal(root.get(column), value));
        return em.createQuery(criteria).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    private Class<T> genericClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}