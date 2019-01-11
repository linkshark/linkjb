package linkjb.utils;

import linkjb.pojo.User;

public class Userutils {
    public static Integer checkMatch(User user,User finduser){
        Integer returnnum=60;
       if((Math.abs((user.getAge()-finduser.getAge()))<=3)){
           returnnum+=10;
       }
//       if(user.getBodyShape().equals(finduser.getBodyShape())){
//           returnnum+=5;
//       }
       if(user.getJob().equals(finduser.getJob())){
           returnnum+=5;
       }
       if((Math.abs((user.getTall()-finduser.getTall()))>=11)){
           returnnum+=15;
        }
        return returnnum;

    };
}
