package com.example.springserver.service;

import com.example.springserver.entity.Recept;
import com.example.springserver.reps.ReceptRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class UpdStatusService {
    @Autowired
    ReceptRep receptRep;
    public boolean changeStatus(long id){
    Recept recept = receptRep.findReceptById(id);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, -(recept.getTerm()));
        System.out.println(calendar.getTime());
        if((recept.getDateof()).after(calendar.getTime())){
            return false;
        }
        else {
            if (recept.getStatus().equals("активен"))
            {
                recept.setStatus("просрочен");
                receptRep.save(recept);
                return true;
            }
            else return false;
        }
    }
}
