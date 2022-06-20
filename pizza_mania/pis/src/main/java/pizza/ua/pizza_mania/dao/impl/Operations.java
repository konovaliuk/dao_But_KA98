package pizza.ua.pizza_mania.dao.impl;

public class Operations {

    public String SELECT(String path){
        return "SELECT * FROM public.\""+path+"\"";
    }

    public String UPDATE(String path, String[] colums){
        String full_update_command = "";
        for(int i = 1; i < colums.length-1; i++){
            full_update_command = full_update_command + colums[i] +"=?, ";
        }
        full_update_command = full_update_command + colums[colums.length-1] +"=?";
        return "UPDATE public.\""+path+"\" SET " + full_update_command + " WHERE " +colums[0] + "=?";
    }

    public String INSERT(String path, String[] colums){
        String full_insert_command = "(";
        String values = "VALUES (";
        for(int i = 1; i < colums.length-1; i++){
            full_insert_command = full_insert_command + colums[i] +",";
            values = values + "?,";
        }
        full_insert_command = full_insert_command + colums[colums.length-1] +") ";
        values = values + "?)";
        return "INSERT INTO public.\""+path+"\"" + full_insert_command + values;
    }

    public String DELETE(String path, String idColumn){
        return "DELETE FROM public.\""+path+"\" WHERE "+idColumn+"=?";
    }
}
