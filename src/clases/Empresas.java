package clases;

public class Empresas extends Clientes {

    private String tipDoc;
    private String documento;
    private String representante;

    private Empresas(){
    }

    public Empresas(String nombre, String telefono, String direccion, String tipoDoc,
                    String documento, String representante){
        super.setNombre(nombre);
        super.setTelefono(telefono);
        super.setDireccion(direccion);
        this.tipDoc = tipoDoc;
        this.documento = documento;
        this.representante = representante;
    }

    public String getTipDoc() {
        return tipDoc;
    }
    public void setTipDoc(String tipDoc) {
        this.tipDoc = tipDoc;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getRepresentante() {
        return representante;
    }
    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    @Override
    public String toString() {
        return super.toString() + "Empresas{" +
                "tipDoc='" + tipDoc + '\'' +
                ", documento='" + documento + '\'' +
                ", representante='" + representante + '\'' +
                '}';
    }
}
