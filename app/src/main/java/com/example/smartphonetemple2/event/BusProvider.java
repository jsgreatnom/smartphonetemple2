package com.example.smartphonetemple2.event;

import com.squareup.otto.Bus;

/**
 * Created by jsyoon on 2017-12-05.
 */

public final class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    private BusProvider(){

    }
}
