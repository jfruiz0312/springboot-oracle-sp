package sv.com.empresa.producto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sv.com.empresa.producto.entity.Producto;
import sv.com.empresa.producto.service.ProductoService;

@Slf4j
@RestController
@RequestMapping("/productos/v1")
public class ProductoController {

	private final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping(path = "/demo/{id}/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String demo(@PathVariable(name = "id") String id, @PathVariable(name = "nombre") String nombre) {
		return "ID: " + id + " Name: " + nombre;
	}

	@GetMapping(path = "/buscar/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> listarPorNombre(@PathVariable("nombre") String nombre) {

		try {
			Producto producto = Producto.builder().nombre(nombre).build();
			List<Producto> lstProductos = productoService.buscar(producto);
			if (lstProductos.size() > 0) {
				return ResponseEntity.ok(lstProductos);
			} else {
				return ResponseEntity.noContent().build();

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}

	}

	@GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> listarTodos() {

		try {
			List<Producto> lstProductos = productoService.listar();
			if (lstProductos.size() > 0) {
				return ResponseEntity.ok(lstProductos);
			} else {
				return ResponseEntity.noContent().build();

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}

	}

	@GetMapping(path = "/buscar-por-id/{idProducto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> listarPorId(@PathVariable("idProducto") String idProducto) {

		try {
			List<Producto> lstProductos = this.productoService.listarPorId(Integer.parseInt(idProducto));
			if (lstProductos.size() > 0) {
				return ResponseEntity.ok(lstProductos);
			} else {
				return ResponseEntity.noContent().build();

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}

	}

	@PostMapping(path = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insertar(@RequestBody Producto producto) {
		Map<String, Object> response = new HashMap<>();
		try {
			productoService.insertar(producto);
			response.put("mensaje", " Se registro el producto en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping(path = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@RequestBody Producto producto) {
		Map<String, Object> response = new HashMap<>();
		try {
			productoService.actualizar(producto);

			response.put("mensaje", "El producto ID: "
					.concat(producto.getId().toString().concat(" se actualizo en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	@DeleteMapping(path = "/eliminar-por-id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@RequestParam String idProducto) {
		Map<String, Object> response = new HashMap<>();

		Integer id = Integer.parseInt(idProducto);

		productoService.eliminar(id);

		response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" se elimino en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
