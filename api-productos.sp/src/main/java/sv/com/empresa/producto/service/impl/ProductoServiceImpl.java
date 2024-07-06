package sv.com.empresa.producto.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sv.com.empresa.producto.entity.Producto;
import sv.com.empresa.producto.repository.ProductoRepository;
import sv.com.empresa.producto.service.ProductoService;

@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService{

	private final ProductoRepository productoRepository;
	
	public ProductoServiceImpl(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public List<Producto> buscar(Producto producto) {
		log.info("Service buscar producto...");
		return productoRepository.buscar(producto);
	}

	@Override
	public List<Producto> listar() {
		log.info("Service listar todos los productos...");
		return productoRepository.listar();
	}

	@Override
	public List<Producto> listarPorId(Integer id) {
		log.info("Service listar productos por id...");
		return productoRepository.listarPorId(id);
	}

	@Override
	public boolean insertar(Producto producto) {
		log.info("Insertar producto...");

		return productoRepository.insertar(producto);
	
	}

	@Override
	public boolean actualizar(Producto producto) {
		log.info("actualizar producto...");
		 return productoRepository.actualizar(producto);
	}

	@Override
	public boolean eliminar(Integer productoId) {
		log.info("eliminar producto...");
		return productoRepository.eliminar(productoId);
	}

	
}
