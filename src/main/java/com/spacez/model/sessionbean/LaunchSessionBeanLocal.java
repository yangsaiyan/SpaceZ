package com.spacez.model.sessionbean;

import com.spacez.model.entity.Launch;

import javax.ejb.EJBException;
import javax.ejb.Local;
import java.util.List;

@Local
public interface LaunchSessionBeanLocal {

    public List<Launch> getAllLaunches() throws EJBException;
    public List<Launch> readLaunches() throws EJBException;
    public int getNumberOfRows(String keyword) throws EJBException;
    public List<Launch> readLaunch(int currentPage, int recordsPerPage, String keyword, String direction) throws EJBException;
    public Launch findLaunch(String id) throws EJBException;
    public void updateLaunch(String[] save) throws EJBException;
    public void deleteLaunch(String id) throws EJBException;
    public void addLaunch(String[] save) throws EJBException;
}
