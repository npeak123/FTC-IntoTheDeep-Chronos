package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.util.NanoClock;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Encoder {
    private final static int CPS_STEP = 0x10000;

    private static double inverseOverFlow(double input, double estimate) {

        int real = (int) input & 0xfffff;

        real += ((real % 20) / 4) * CPS_STEP;

        real += Math.round((estimate - real) / (5 * CPS_STEP)) * 5 * CPS_STEP;
        return real;
    }

    public enum Directionn {
        FORWARD(1),
        REVERSE(-1);

        private int multiplier;

        Direction(int multiplier) {
            this.multiplier = multiplier;
        }

        public int getMultiplier() {
            return multiplier;
        }
    }

    private DcMotorEx motor;
    private NanoClock clock;

}
