package com.spacez.model.sessionbean;

import com.spacez.model.entity.Launch;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class LaunchSessionBean implements LaunchSessionBeanLocal{

    @PersistenceContext(unitName = "SpaceZ")
    EntityManager em;


    public List<Launch> getAllLaunches() throws EJBException {

        return em.createNamedQuery("Launch.findAll").getResultList();

    }

    public List<Launch> readLaunches() throws EJBException {

        return em.createNamedQuery("Launch.findAll").getResultList();

    }

    public int getNumberOfRows(String keyword) throws EJBException{
        Query q = null;
        if(keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT COUNT(*) FROM launch");
        }
        else{

            q = em.createNativeQuery("SELECT COUNT(*) FROM launch WHERE lower(concat(\"Launch_ID\", \"Mission_Name\", \"SpaceCraft_Model\", \"Date\", \"Time\", \"Is_Launch\")) LIKE lower(:keyword)");
            q.setParameter("keyword", "%" + keyword + "%");
        }
        BigInteger results = (BigInteger)  q.getSingleResult();
        int i = results.intValue();
        return i;
    }

    public List<Launch> readLaunch(int currentPage, int recordsPerPage, String keyword, String direction) throws EJBException{
        Query q = null;
        int start = 0;
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT * FROM launch order by \"Launch_ID\" " + direction, Launch.class);
            start = currentPage * 6 - 6;
        }
        else if (!keyword.isEmpty()){

            String queryStr = "SELECT * from launch WHERE lower(concat(\"Launch_ID\", \"Mission_Name\", \"SpaceCraft_Model\", \"Date\", \"Time\", \"Is_Launch\")) LIKE lower(:keyword) order by \"Launch_ID\"";

            if (direction != null && !direction.isEmpty()) {
                queryStr += " " + direction;
            }
            q = em.createNativeQuery(queryStr, Launch.class);
            q.setParameter("keyword", "%" + keyword + "%");
            start = currentPage * 6 - 6;
        }
        List<Launch> results = q.setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
        return results;
    }

    public Launch findLaunch(String id) throws EJBException{

        Query q = em.createNamedQuery("Launch.findbyId");
        q.setParameter("id", Integer.valueOf(id));
        return (Launch) q.getSingleResult();
    }

    public void updateLaunch(String[] save) throws EJBException{
        //String[] save = {lid, name, model, date, time, desc, is_launch};
        Launch l = findLaunch(save[0]);
        l.setMissionName(save[1]);
        l.setSpacecraftModel(save[2]);
        l.setDate(LocalDate.parse(save[3]));
        l.setTime(Time.valueOf(save[4]));
        l.setDescription(save[5]);
        l.setIsLaunched(Boolean.parseBoolean(save[6]));
        em.merge(l);
    }

    public void deleteLaunch(String id) throws EJBException{
        Launch l = findLaunch(id);
        em.remove(l);
    }

    public void addLaunch(String[] save) throws EJBException{

        //String[] save = {lid, name, model, date, time, desc, is_launch};
        String desc = "";
        desc = save[5];
        String check = "";
        check = save[6];
        Launch l = new Launch();
        l.setId(Integer.valueOf(save[0]));
        l.setMissionName(save[1]);
        l.setSpacecraftModel(save[2]);
        l.setDate(LocalDate.parse(save[3]));
        l.setTime(Time.valueOf(save[4]));
        l.setDescription(desc);
        l.setIsLaunched(Boolean.parseBoolean(check));
        em.persist(l);
    }
}
