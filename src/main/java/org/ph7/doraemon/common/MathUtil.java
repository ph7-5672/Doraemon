package org.ph7.doraemon.common;

public class MathUtil
{
    public static double[] rotateX(double angle, double x, double y, double z)
    {
        angle = Math.toRadians(angle);
        return new double[]{x, y * Math.cos(angle) + z * Math.sin(angle), -(y * Math.sin(angle)) + z * Math.cos(angle)};
    }

    public static double[] rotateY(double angle, double x, double y, double z)
    {
        angle = Math.toRadians(angle);
        return new double[]{x * Math.cos(angle) - z * Math.sin(angle), y, x * Math.sin(angle) + z * Math.cos(angle)};
    }

    public static double[] rotateZ(double angle, double x, double y, double z)
    {
        return new double[]{x * Math.cos(angle) + y * Math.sin(angle), -(x * Math.sin(angle)) + y * Math.cos(angle), z};
    }
}
