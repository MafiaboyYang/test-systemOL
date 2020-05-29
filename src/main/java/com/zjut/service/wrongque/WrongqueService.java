package com.zjut.service.wrongque;

import java.util.List;


import com.zjut.pojo.Wrongque;
import com.zjut.utils.Page;

public interface WrongqueService {

    public List<Wrongque> findWrongquebyuserID(int userId);
}
