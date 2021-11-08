/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.ArrayList;

/**
 *
 * @author XUÂN THÀNH
 */
public interface QLSVInterface<E,T> {
    public boolean insert(E e)throws Exception;
    public boolean update(E e)throws Exception;
    public boolean delete(T maSV)throws Exception;
    public E timTheoMa(T maSV)throws Exception;
    public ArrayList<E> getDanhSachSV()throws Exception;
    
}
