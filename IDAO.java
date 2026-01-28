package com.room911.service.DAO;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica DAO (Data Access Object) para operaciones CRUD básicas.
 *
 * @param <T> Tipo de la entidad
 * @param <ID> Tipo del identificador de la entidad
 */
public interface IDAO<T, ID> {

    /**
     * Guarda o actualiza una entidad.
     */
    T save(T entity);

    /**
     * Busca una entidad por su ID.
     */
    Optional<T> findById(ID id);

    /**
     * Obtiene todas las entidades.
     */
    List<T> findAll();

    /**
     * Elimina una entidad por su ID.
     */
    void deleteById(ID id);

    /**
     * Elimina una entidad.
     */
    void delete(T entity);

    /**
     * Verifica si existe una entidad con el ID dado.
     */
    boolean existsById(ID id);

    /**
     * Cuenta el total de entidades.
     */
    long count();
}
