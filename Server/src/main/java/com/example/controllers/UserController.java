package com.example.controllers;

import com.example.entities.Pacient;
import com.example.entities.Recept;
import com.example.entities.Receptpreparat;
import com.example.entities.User;
import com.example.handlers.UserHandler;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.validation.constraints.Size;
import java.beans.beancontext.BeanContext;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.*;

@Controller("/Users")
public class UserController {
    //ручное изменение рецепта
    @Post(value = "/changeSt" , consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<String> idr(int idrec) {

//        if (changeS(idrec))
//        {
            changeS(idrec);
            String response = "OK";

            return HttpResponse.ok().body("{\"msg\":\"response\"}");
            //return HttpResponse.ok().body(response+"{\"msg\":\"ok\", \"polis\":\""+pacient.polis+"\", \"f\":\""+pacient.f+"\" , \"i\":\""+pacient.i+"\" , \"o\":\""+pacient.o+"\"}");

//        }
//        else {
//            return HttpResponse.ok().body("{\"msg\":\"Не\"}");
//        }
    }

    //доделать
    @Post(value = "/getLec" , consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<String> rec(int idpac) {

        if (!(gPrep(idpac).isEmpty()))
        {
            String response = "";
            //Receptpreparat receptpreparat = gPac(polis).get(0);
                response += "{ \"leclist\" : [";
                ListIterator<Receptpreparat> preps = gPrep(idpac).listIterator();
                while (preps.hasNext())
                {
                    Receptpreparat receptpreparat = preps.next();
                    response +="{\"id\":\""+receptpreparat.id+"\"," +
                            "\"sppr\":\""+receptpreparat.sppr+"\"," +
                            "\"edizm\":\""+receptpreparat.edizm+"\"," +
                            "\"vipicano\":\""+receptpreparat.vipicano+"\"," +
                            "\"doza\":\""+receptpreparat.doza+"\"," +
                            "\"kolvodoz\":\""+receptpreparat.kolvodoz+"\"," +
                            "\"kolvokurs\":\""+receptpreparat.kolvokurs+"\"," +
                            "\"kurs\":\""+receptpreparat.kurs+"\"," +
                            "\"idrec\":\""+receptpreparat.idrec+"\"," +
                            "\"idpre\":\""+receptpreparat.idpre+"\"},";
                }
                return HttpResponse.ok().body(response.substring(0,response.length()-1)+"]}");
                //return HttpResponse.ok().body(response+"{\"msg\":\"ok\", \"polis\":\""+pacient.polis+"\", \"f\":\""+pacient.f+"\" , \"i\":\""+pacient.i+"\" , \"o\":\""+pacient.o+"\"}");

        }
        else {
            return HttpResponse.ok().body("{\"msg\":\"Не\"}");
        }
    }
    //вход
    @Post(value = "/getPac" , consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<String> pac(String polis, List<String> filter)
    {

        if (!(gPac(polis).isEmpty()))
        {
            String response = "";
            Pacient pacient = gPac(polis).get(0);
            if (gRec(pacient.id).isEmpty())
            {
                return HttpResponse.ok().body("{\"msg\":\"ok\", \"polis\":\""+pacient.polis+"\", \"f\":\""+pacient.f+"\" , \"i\":\""+pacient.i+"\" , \"o\":\""+pacient.o+"\", \"rec\":[]}");
            }
            else {
                if(filter.isEmpty())
                {
                    response += "{\"msg\":\"ok\", \"polis\":\""+pacient.polis+"\", \"f\":\""+pacient.f+"\" , \"i\":\""+pacient.i+"\" , \"o\":\""+pacient.o+"\", \"recepts\":[";
                    ListIterator<Recept> recepts = gRec(pacient.id).listIterator();
                    while (recepts.hasNext())
                    {
                        Recept recept = recepts.next();
                        response +="{\"id\":"+recept.id+"," +
                                "\"dateof\":\""+recept.dateo.substring(0,recept.dateo.length()-7)+"\"," +
                                "\"srok\":"+recept.srok+"," +
                                "\"status\":\""+recept.status+"\"," +
                                "\"diagnoz\":\""+recept.diagnoz+"\"},";
                    }
                    return HttpResponse.ok().body(response.substring(0,response.length()-1)+"]}");
                }
                else {
                    return HttpResponse.ok().body("{\"msg\":\"Не\"}");//cуда фильтр+(метод написать)
                }
                //return HttpResponse.ok().body(response+"{\"msg\":\"ok\", \"polis\":\""+pacient.polis+"\", \"f\":\""+pacient.f+"\" , \"i\":\""+pacient.i+"\" , \"o\":\""+pacient.o+"\"}");
            }
        }
        else {
            return HttpResponse.ok().body("{\"msg\":\"Не\"}");
        }


    }


    //-------------------------------------------------------------
    //Тест
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @Post("/Test")
    public HttpResponse<String> test(String name, String token) {

        if(tokenList.contains(token))
        {return HttpResponse.ok().body("{\"msg\":\""+ name +"\"}");}
        else
        {
            return HttpResponse.ok().body("{\"msg\":\"Wrong token\"}");
        }
    }
    @Post(value = "/echo", consumes = MediaType.APPLICATION_JSON)
    String echo(@Size(max = 1024) @Body String text) {
        return text;
    }
//-------------------------------------------------------------
    @Post(value = "/Register" , consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<String> reg(String login,String pass,String role) {
        if(checkUnique(login).isEmpty())
        {
            addU(new User(1, login,pass, role));
            return HttpResponse.ok().body("{\"msg\":\"ok\",\"token\":\""+token()+"\"}");
        }
        else {
            return HttpResponse.ok().body("{\"msg\":\"login zanyat\"}");
        }
    }
    @Post(value = "/Login" , consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<String> log(String login,String pass) {
        if(!(checkLog(login,pass).isEmpty()))
        {
            return HttpResponse.ok().body("{\"msg\":\"okLOg\",\"token\":\""+token()+"\"}");
        }
        else {
            return HttpResponse.ok().body("{\"msg\":\"neokLOg\"}");
        }
    }
    //-------------------------------------------------------------
    private static final String conn_s = "jdbc:postgresql://45.10.244.15:55532/work100016";
    private List<String> tokenList = new ArrayList<String>();

    public void changeS(int id){
        try{
            Connection con= DriverManager.getConnection(conn_s,"work100016","{FXcadFL99Ncvo?kOMW~");
            PreparedStatement statement = con.prepareStatement("update recept set status = 'обслужен' where idrec = ?");
            statement.setObject (1, id);
            statement.execute();
        } catch (SQLException e){ e.printStackTrace();}
    }
    private List<Receptpreparat> gPrep(int idrec){
        try{
            Connection con= DriverManager.getConnection(conn_s,"work100016","{FXcadFL99Ncvo?kOMW~");
            Statement statement = con.createStatement();
            String q = String.format("SELECT idprerec, sppr, edizm, vipisano, doza, kolvodoz, kolvokurs, kurs " +
                    "FROM preparatrecept JOIN preparat ON preparatrecept.idpre = preparat.idpre " +
                    "where idrec = %d",idrec);
            List<Receptpreparat> receptpreparats = new ArrayList<Receptpreparat>();
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                receptpreparats.add(new Receptpreparat(
                        resultSet.getInt("idprerec"),
                        resultSet.getString("sppr"),
                        resultSet.getString("edizm"),
                        resultSet.getInt("vipisano"),
                        resultSet.getInt("doza"),
                        resultSet.getInt("kolvodoz"),
                        resultSet.getInt("kolvokurs"),
                        resultSet.getString("kurs")));
            }
            statement.close();
            con.close();
            return receptpreparats;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    private List<Pacient> gPac(String polis){
        try{
            Connection con= DriverManager.getConnection(conn_s,"work100016","{FXcadFL99Ncvo?kOMW~");
            Statement statement = con.createStatement();
            String q = String.format("SELECT * FROM pacient WHERE polis = '%s'",polis);
            List<Pacient> pacients = new ArrayList<Pacient>();
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                pacients.add(new Pacient(resultSet.getInt("idpac"),
                        resultSet.getString("f"),
                        resultSet.getString("i"),
                        resultSet.getString("o"),
                        resultSet.getString("polis")));
            }
            statement.close();
            con.close();
            return pacients;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    private List<Recept> gRec(int id){
        try{
            Connection con= DriverManager.getConnection(conn_s,"work100016","{FXcadFL99Ncvo?kOMW~");
            Statement statement = con.createStatement();
            String q = String.format("SELECT * FROM recept WHERE idpac = %d",id);
            List<Recept> recepts = new ArrayList<Recept>();
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                recepts.add(new Recept(resultSet.getInt("idrec"),
                        resultSet.getString("dateof"),
                        resultSet.getInt("srok"),
                        resultSet.getString("status"),
                        resultSet.getString("diagnoz"),
                        resultSet.getString("qr"),
                        resultSet.getInt("iddoc"),
                        resultSet.getInt("idpac")));
            }
            statement.close();
            con.close();
            return recepts;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public List<User> checkLog(String login, String pass) {
        try{
            Connection con= DriverManager.getConnection(conn_s,"work100016","{FXcadFL99Ncvo?kOMW~");
            Statement statement = con.createStatement();
            String q = String.format("SELECT * FROM users WHERE login = '%s' AND pass = '%s'",login,pass);
            List<User> users = new ArrayList<User>();
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("pass"),
                        resultSet.getString("role")));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public List<User> checkUnique(String login) {
        try{
            Connection con= DriverManager.getConnection(conn_s,"work100016","{FXcadFL99Ncvo?kOMW~");
            Statement statement = con.createStatement();
            String q = String.format("SELECT * FROM users WHERE login ='%s' ",login);
            List<User> users = new ArrayList<User>();
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("pass"),
                        resultSet.getString("role")));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addU(User user){
        try{
            Connection con= DriverManager.getConnection(conn_s,"work100016","{FXcadFL99Ncvo?kOMW~");
            PreparedStatement statement = con.prepareStatement("INSERT INTO users(login, pass, role) " + "VALUES(?,?,?)");
            statement.setObject (1, user.login);
            statement.setObject (2, user.pass);
            statement.setObject (3, user.role);
            statement.execute();
        } catch (SQLException e){ e.printStackTrace();}
    }
    public String token() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        tokenList.add(array.toString());
        System.out.println(array.toString());
        return array.toString();
    }
}
