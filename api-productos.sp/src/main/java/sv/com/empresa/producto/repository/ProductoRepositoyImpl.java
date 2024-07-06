package sv.com.empresa.producto.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import sv.com.empresa.producto.entity.Producto;

@Slf4j
@Repository
public class ProductoRepositoyImpl implements ProductoRepository{
	
	@PersistenceContext 
	private EntityManager entityManager;  //Unidad de persistencia
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> buscar(Producto producto) {
		try {

			StoredProcedureQuery spq = entityManager.createNamedStoredProcedureQuery("producto.listar");
			spq.setParameter("P_NOMBRE", producto.getNombre());
			
			if (spq.execute()) {
				List<Producto> resultado = (List<Producto>) spq.getResultList();
				 log.info("Listado de producto  por nombre..." +resultado.toString());
					return resultado;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			entityManager.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> listar() {
		try {
			StoredProcedureQuery spq = entityManager.createNamedStoredProcedureQuery("producto.listarTodo");

			if (spq.execute()) {
				List<Producto> resultado = (List<Producto>) spq.getResultList();
				log.info("Listado de productos..." + resultado.toString());
				return resultado;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			entityManager.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> listarPorId(Integer id) {
		try {
			StoredProcedureQuery spq = entityManager.createNamedStoredProcedureQuery("producto.listarPorId");
			spq.setParameter("P_ID_PRODUCTO", id);

			if (spq.execute()) {
				List<Producto> resultado = (List<Producto>) spq.getResultList();
				log.info("Listado de productos por id..." + resultado.toString());
				return resultado;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			entityManager.close();
		}

		return null;
	}

	@Override
	public boolean insertar(Producto producto) {
		boolean sw = false;

		try {
			StoredProcedureQuery spq = entityManager.createNamedStoredProcedureQuery("producto.insertar");
			
			spq.setParameter("P_NOMBRE", producto.getNombre());
			spq.setParameter("P_PRECIO", producto.getPrecio());
			spq.setParameter("P_STOCK", producto.getStock());


			if (spq.execute()) {
				sw = true;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			log.info("Cerrando entityManager al insertar...");
			entityManager.close();
		}

		return sw;
	}

	@Override
	public boolean actualizar(Producto producto) {
		boolean sw = false;

		try {
			StoredProcedureQuery spq = entityManager.createNamedStoredProcedureQuery("producto.actualizar");
		
			spq.setParameter("P_ID_PRODUCTO", producto.getId());
			spq.setParameter("P_NOMBRE", producto.getNombre());
			spq.setParameter("P_PRECIO", producto.getPrecio());
			spq.setParameter("P_STOCK", producto.getStock());

			if (spq.execute()) {
				sw = true;
				log.info("producto actualizado....." + sw);

			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			log.info("Cerrando entityManager al actualizar...");
			entityManager.close();
		}

		return sw;
	}

	@Override
	public boolean eliminar(Integer productoId) {
		boolean sw = false;

		try {
			StoredProcedureQuery spq = entityManager.createNamedStoredProcedureQuery("producto.eleminar");
		
			spq.setParameter("P_ID_PRODUCTO", productoId);
			

			if (spq.execute()) {
				sw = true;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			log.info("Cerrando entityManager al eliminar...");
			entityManager.close();
		}

		return sw;
	}

}
