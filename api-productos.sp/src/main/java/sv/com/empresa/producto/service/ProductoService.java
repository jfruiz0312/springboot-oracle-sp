package sv.com.empresa.producto.service;

import java.util.List;

import sv.com.empresa.producto.entity.Producto;

public interface ProductoService {

	List<Producto> buscar(Producto producto);

	List<Producto> listar();
	
	List<Producto> listarPorId(Integer id);
	
	boolean insertar(Producto producto);
	
	boolean actualizar(Producto producto);
	
	boolean eliminar(Integer productoId);
}
