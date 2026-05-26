package service;

import dao.BlockDAO;
import java.util.List;

public class BlockService {

    BlockDAO blockDAO = new BlockDAO();
    public boolean blockUser(int blocker,int blocked){
        return blockDAO.blockUser(blocker,blocked);
    }

    public List<Integer> getBlockedUsers(int userId){
        return blockDAO.getBlockedUsers(userId);
    }
}