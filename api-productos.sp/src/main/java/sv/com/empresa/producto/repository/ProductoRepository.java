package sv.com.empresa.producto.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import sv.com.empresa.producto.entity.Producto;

@Repository
public interface ProductoRepository {

	List<Producto> buscar(Producto producto);

	List<Producto> listar();
	
	List<Producto> listarPorId(Integer id);
	
	boolean insertar(Producto producto);
	
	boolean actualizar(Producto producto);
	
	boolean eliminar(Integer productoId);


}
