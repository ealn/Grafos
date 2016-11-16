package classes;

public class Grafo 
{
    private static final int INFINITY = 2147483647;
    
    public static String[] nodes = { "Guadalajara", 
                                     "Leon", 
                                     "Aguascalientes", 
                                     "Zacatecas", 
                                     "Durango",
                                     "San Luis Potosi",
                                     "Mazatlan",
                                     "Tepic",
                                     "Mexico",
                                     "Monterrey"};
    
    public static int[][] connections = { /* Gdl      Leon      Ags       Zacatecas Durango   San Luis  Mazatlan  Tepic     Mexico    Monterrey */
                /* Guadalajara        */  { INFINITY, 2,        2,        INFINITY, INFINITY, INFINITY, INFINITY, 3,        5,        INFINITY },
                /* Leon               */  { 2,        INFINITY, 1,        INFINITY, INFINITY, 2,        INFINITY, INFINITY, 4,        INFINITY },
                /* Aguascalientes     */  { 2,        1,        INFINITY, 1,        INFINITY, 2,        INFINITY, INFINITY, INFINITY, INFINITY },
                /* Zacatecas          */  { INFINITY, INFINITY, 1,        INFINITY, 3,        2,        INFINITY, INFINITY, INFINITY, 5        },
                /* Durango            */  { INFINITY, INFINITY, INFINITY, 3,        INFINITY, INFINITY, 2,        INFINITY, INFINITY, 6        },
                /* San Luis Potosi    */  { INFINITY, 2,        2,        2,        INFINITY, INFINITY, INFINITY, INFINITY, 5,        5        },
                /* Mazatlan           */  { INFINITY, INFINITY, INFINITY, INFINITY, 2,        INFINITY, INFINITY, 3,        INFINITY, INFINITY },
                /* Tepic              */  { 3,        INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 3,        INFINITY, INFINITY, INFINITY },
                /* Mexico             */  { 5,        4,        INFINITY, INFINITY, INFINITY, 5,        INFINITY, INFINITY, INFINITY, INFINITY },
                /* Monterrey          */  { INFINITY, INFINITY, INFINITY, 5,        6,        5,        INFINITY, INFINITY, INFINITY, INFINITY }};

}
