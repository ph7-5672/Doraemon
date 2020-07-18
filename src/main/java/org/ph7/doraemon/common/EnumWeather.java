package org.ph7.doraemon.common;

public enum EnumWeather
    {
        SUNNY(0, "weather.clear"),
        RAIN(1, "weather.rain"),
        THUNDER(2, "weather.thunder"),
        SNOW(3, "weather.snow"),
        RAIN_SNOW(4, "weather.rain_snow"),
        ;
        private int meta;
        private String key;

        EnumWeather(int meta, String key)
        {
            this.meta = meta;
            this.key = key;
        }

        public String getKey()
        {
            return key;
        }

        public int getMeta()
        {
            return meta;
        }

        public static EnumWeather getByMeta(int meta)
        {
            for (EnumWeather weather : EnumWeather.values())
            {
                if (weather.getMeta() == meta)
                {
                    return weather;
                }
            }
            return null;
        }
    }