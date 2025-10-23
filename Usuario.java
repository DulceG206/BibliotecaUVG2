public class Usuario{
    private String nombre; 
    private String email; 
    private int carnet; 

    public Usuario(String nombre, String email, int carnet){

    }
    public String getNombre(){
        return nombre;
    }
     public String verLibrosApartados() {
        return "Libros apartados"; 
    }
    public boolean tieneLibros(){
        return true; 
    }
}