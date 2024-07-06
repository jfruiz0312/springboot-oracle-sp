package sv.com.empresa.producto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
//Matricula UPSs
@NamedStoredProcedureQueries(
		{
			@NamedStoredProcedureQuery(
					name = "producto.listar",
					procedureName="DEV.PKG_PRODUCTO.SP_LISTAR",
					resultClasses = Producto.class,
					parameters = {
							@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_CURSOR",  type=void.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class)

					}
					),
			@NamedStoredProcedureQuery(
					name = "producto.listarTodo",
					procedureName="DEV.PKG_PRODUCTO.SP_LISTAR_TODO",
					resultClasses = Producto.class,
					parameters = {
							@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_CURSOR",  type=void.class),
					}
					),
			@NamedStoredProcedureQuery(
					name = "producto.listarPorId",
					procedureName="DEV.PKG_PRODUCTO.SP_BUSCAR_X_ID_PRODUCTO",
					resultClasses = Producto.class,
					parameters = {
							@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_CURSOR",  type=void.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PRODUCTO", type=Integer.class)

					}
				),
				@NamedStoredProcedureQuery(
						name = "producto.insertar", 
						procedureName = "DEV.PKG_PRODUCTO.SP_INSERTAR", 
						resultClasses = Producto.class, 
						parameters = {
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_ID_PRODUCTO", type = Long.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_NOMBRE", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PRECIO", type = Double.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_STOCK", type = Integer.class) 
						}
						),
				@NamedStoredProcedureQuery(
						name="producto.actualizar", 
						procedureName="DEV.PKG_PRODUCTO.SP_ACTUALIZAR",
						resultClasses= Producto.class,
						parameters={
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PRODUCTO", type=Integer.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_PRECIO", type=Double.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_STOCK", type=Integer.class)					
			
						}						
				),
				
				@NamedStoredProcedureQuery(
						name="producto.eliminar",//Eliminacion logica de nivel de BD..
						procedureName="DEV.PKG_PRODUCTO.SP_ELIMINAR",
						parameters={
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PRODUCTO", type=Integer.class),
										}					
				)

		}
		)
@Data
@Builder
@Entity(name = "Producto")
@Table(name = "producto")
@NoArgsConstructor @AllArgsConstructor 
public class Producto {

	@Id
	@Column(name="PRODUCTO_ID", nullable=false)
	private Integer id;

	@NotNull(message = "El nombre no debe ser nulo")
	@Size(min = 5, max = 120, message = "El nombre es querido y debe tener como mínimo {min} y máximo {max} caracteres")
	@Column(name="NOMBRE", nullable=false)
	private String nombre;

	@Positive(message = "El precio debe ser positivo")
	@Column(name="PRECIO", nullable=false)
	private Double precio;
	
	@PositiveOrZero(message = "El stock debe ser positivo o cero")
	@Column(name="STOCK", nullable=false)
	private Integer stock;

	@NotNull(message = "El estado es equerido")
	@Column(name="ESTADO", nullable=false)
	private String estado;
	
	
}
