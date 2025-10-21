public class Usuario{
    private String nombre; 
    private String email; 
    private int carnet; 

    public void Usuario(String nombre, String email, int carnet){

    }
    public String getNombre(){
        return nombre;
    }
    public String verLibrosApartados(libro Libro){
        return Libro; 
    }
    public boolean tieneLibros(){
        return true; 
    }
}