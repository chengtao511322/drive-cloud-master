package com.drive.admin.design.context;


import com.drive.admin.design.StatusState;
import com.drive.common.core.biz.ResObject;
import org.springframework.stereotype.Component;

@Component
public class ContextState {

    private StatusState statusState;

/*
    public ContextState(StatusState statusState) {
        this.statusState = statusState;
    }
*/


    public ResObject switchStateStatus() {
        return statusState.statusService();
    }


}
