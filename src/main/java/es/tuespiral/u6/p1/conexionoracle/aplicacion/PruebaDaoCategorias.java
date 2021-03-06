package es.tuespiral.u6.p1.conexionoracle.aplicacion;

import es.tuespiral.u6.p1.conexionoracle.negocio.ProductCategory;
import es.tuespiral.u6.p1.conexionoracle.persistencia.dao.excepciones.DataAccessException;
import java.util.List;
import java.util.Scanner;

public class PruebaDaoCategorias {

    public static void main(String args[]) throws DataAccessException{
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMENU CATEGORÍAS DE PRODUCTO");
            System.out.println("0 - Salir");
            System.out.println("1 - Consultar todas");
            System.out.println("2 - Consultar por ID");
            System.out.println("3 - Crear un nueva categoría");
            System.out.println("4 - Actualizar una categoría existente");
            System.out.println("5 - Eliminar una categoría");
            System.out.println("Elige una opción:");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 0:
                    System.out.println("Adios...");
                    break;
                case 1:
                    List<ProductCategory> lista = ProductCategory.consultarTodos();
                    for(ProductCategory pc : lista) {
                        System.out.println(pc);
                    }
                    break;
                case 2:
                    System.out.println("Dime el ID de la categoría");
                    long id = sc.nextLong();
                    try {
                        ProductCategory resultado = ProductCategory.consultarPorId(id);
                        System.out.println(resultado);
                    } catch(DataAccessException e) {
                        System.out.println("Categoría inexistente");
                    }
                    break;
                case 3:
                    System.out.println("Dime el nombre de la nueva categoría");
                    String nombre = sc.nextLine();
                    ProductCategory nueva = new ProductCategory(null, nombre);
                    nueva.guardar();
                    System.out.println("La categoría se creó correctamente");
                    break;
                case 4:
                    System.out.println("Dime el ID de categoría a actualizar");
                    Long idActualiza = sc.nextLong();
                    sc.nextLine(); // Vaciamos el buffer
                    
                    try {
                        ProductCategory categ = ProductCategory.consultarPorId(idActualiza);
                        System.out.println("El nombre actual de la categoría es: "+categ.getCategoryName());
                        System.out.println("Dime el nuevo nombre de la categoría (pulsa INTRO para dejarlo igual)");
                        String nuevoNombre = sc.nextLine();
                        if (!nuevoNombre.equals("")) {
                            categ.setCategoryName(nuevoNombre);
                            categ.guardar();
                            System.out.println("La categoría se actualizó correctamente");
                        }
                    } catch(DataAccessException e) {
                        System.out.println("Categoría inexistente");
                    }
                    
                    break;
                case 5:
                    System.out.println("Dime el ID de categoría a eliminar");
                    Long idElimina = sc.nextLong();
                    sc.nextLine(); // Vaciamos el buffer
                    
                    try {
                        ProductCategory categ = ProductCategory.consultarPorId(idElimina);
                        System.out.println("La categoría a eliminar es: "+categ.getCategoryName());
                        System.out.println("Estás seguro (S = Sí / N = No)");
                        String respuesta = sc.nextLine();
                        
                        if (respuesta.equalsIgnoreCase("S") || 
                            respuesta.equalsIgnoreCase("Sí") || 
                            respuesta.equalsIgnoreCase("Si")) {
                            categ.eliminar();
                            System.out.println("La categoría se eliminó correctamente");
                        }
                    } catch(DataAccessException e) {
                        System.out.println("Categoría inexistente");
                    }
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }

        } while (opcion != 0);
    }
}
