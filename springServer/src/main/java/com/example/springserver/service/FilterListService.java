package com.example.springserver.service;

import com.example.springserver.entity.Recept;
import com.example.springserver.reps.ReceptRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilterListService {
    @Autowired
    ReceptRep receptRep;
    public List<Recept> filterReceptList(long id, Date startdate, Date enddate, boolean activeflag, boolean disactiveflag, boolean timeoutflag){
        if((activeflag && disactiveflag && timeoutflag)||(!activeflag && !disactiveflag && !timeoutflag)){
            System.out.println("1");
            return receptRep.findReceptsByPatientId(id);
        }
        else {
            System.out.println("2");
            List<Recept> recepts1 = receptRep.findReceptsByPatientId(id);
            //List<Recept> recepts = (ArrayList<Recept>)recepts1.clone();
            List<Recept> recepts = new ArrayList<Recept>(recepts1);
            //Collections.copy(recepts,recepts1);
            long delid = 0;
            Iterator<Recept> iterator = recepts.iterator();
            while(iterator.hasNext()) {
                Recept recept = iterator.next();
                if(recept.getDateof().after(startdate)){
                    System.out.println("3");
                    if(recept.getDateof().before(enddate)){
                        System.out.println("4");
                        System.out.println(recept.getStatus().equals("активен"));
                        if((recept.getStatus().equals("активен")) && activeflag) {
                            System.out.println("5");
                        }else if((recept.getStatus().equals("обслужен")) && disactiveflag)
                        {
                            System.out.println("6");
                        }else if((recept.getStatus().equals("просрочен")) && timeoutflag)
                        {
                            System.out.println("7");
                        }
                        else{
                            recepts.remove(recept);
                            System.out.println("8");
                        }
                    }
                    else {
                        recepts.remove(recept);
                        System.out.println("9");
                    }
                }
                else {
                    recepts.remove(recept);
                    System.out.println("10");
                }
            }
            //recepts.clear();
            System.out.println(recepts);
            return recepts;
        }
    }
}
