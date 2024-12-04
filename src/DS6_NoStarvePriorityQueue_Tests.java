
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class DS6_NoStarvePriorityQueue_Tests
{
    int[][] addsP = {{1, 23, 6, 25, 30, 21, 16, 26, 14},
            {30, 26, 15, 1, 19, 11},
            {30, 17, 29, 11, 17, 15},
            {4, 17, 4, 18, 15, 25, 16, 13, 11, 29, 4},
            {24, 21, 21, 14, 12, 19, 3, 30},
            {20, 6, 11, 28, 23, 27, 4, 5, 18},
            {8, 3, 10, 1, 20, 26, 3},
            {20, 8, 29, 23, 20, 28, 11, 3, 9, 1, 23, 13, 1},
            {3, 19, 17, 7, 16, 27, 4, 26, 30, 24, 7, 20},
            {7, 4, 28, 3, 16, 21, 4, 19, 28, 27, 10, 25},
            {16, 6, 17, 5, 18, 10, 5, 23},
            {28, 24, 4, 30, 30, 2, 23, 28, 23, 23, 9, 12},
            {5, 14, 18, 8, 6, 5, 7, 21, 25, 3, 1},
            {2, 18, 9, 21, 15, 20, 28, 13, 7, 27, 20, 18},
            {8, 13, 3, 11, 3, 7, 3, 16, 26, 14, 24, 15, 14},
            {16, 22, 2, 21, 2, 10, 29, 3, 19, 4},
            {22, 8, 21, 23, 27, 23, 29, 22, 28, 18, 25, 4, 22},
            {11, 16, 10, 9, 3, 29, 16, 21, 17, 5, 12, 9, 8},
            {9, 15, 18, 22, 22, 20, 5, 10, 12, 6}};
    String[][] addsV = {{"q", "x", "j", "q", "u", "n", "t", "w", "u"},
            {"j", "g", "w", "g", "r", "q"},
            {"j", "n", "q", "i", "w", "z"},
            {"u", "s", "p", "s", "j", "r", "l", "v", "f", "y", "s"},
            {"x", "j", "z", "t", "a", "x", "d", "a"},
            {"q", "l", "z", "l", "z", "q", "y", "b", "s"},
            {"x", "u", "n", "h", "v", "z", "x"},
            {"o", "z", "d", "t", "p", "s", "y", "n", "z", "b", "q", "r", "t"},
            {"i", "k", "j", "a", "p", "l", "k", "i", "u", "e", "m", "s"},
            {"b", "y", "p", "f", "m", "u", "y", "j", "f", "d", "p", "b"},
            {"b", "l", "w", "d", "h", "x", "k", "y"},
            {"j", "o", "v", "u", "i", "y", "k", "y", "m", "v", "j", "b"},
            {"d", "j", "c", "y", "i", "y", "x", "q", "z", "i", "s"},
            {"f", "m", "v", "v", "y", "w", "n", "x", "p", "e", "a", "d"},
            {"u", "l", "s", "z", "p", "m", "q", "q", "f", "n", "m", "i", "v"},
            {"r", "x", "u", "w", "i", "u", "e", "q", "m", "u"},
            {"o", "s", "b", "o", "k", "e", "j", "v", "z", "r", "q", "u", "f"},
            {"h", "w", "s", "o", "m", "r", "t", "u", "g", "r", "y", "s", "q"},
            {"g", "s", "j", "f", "o", "e", "j", "f", "v", "e"}};
    String[] toStrings = {"[(q, 1), (j, 6), (u, 14), (t, 16), (n, 21), (x, 23), (q, 25), (w, 26), (u, 30)]",
            "[(g, 1), (q, 11), (w, 15), (r, 19), (g, 26), (j, 30)]",
            "[(i, 11), (z, 15), (n, 17), (w, 17), (q, 29), (j, 30)]",
            "[(u, 4), (p, 4), (s, 4), (f, 11), (v, 13), (j, 15), (l, 16), (s, 17), (s, 18), (r, 25), (y, 29)]",
            "[(d, 3), (a, 12), (t, 14), (x, 19), (j, 21), (z, 21), (x, 24), (a, 30)]",
            "[(y, 4), (b, 5), (l, 6), (z, 11), (s, 18), (q, 20), (z, 23), (q, 27), (l, 28)]",
            "[(h, 1), (u, 3), (x, 3), (x, 8), (n, 10), (v, 20), (z, 26)]",
            "[(b, 1), (t, 1), (n, 3), (z, 8), (z, 9), (y, 11), (r, 13), (o, 20), (p, 20), (t, 23), (q, 23), (s, 28), (d, 29)]",
            "[(i, 3), (k, 4), (a, 7), (m, 7), (p, 16), (j, 17), (k, 19), (s, 20), (e, 24), (i, 26), (l, 27), (u, 30)]",
            "[(f, 3), (y, 4), (y, 4), (b, 7), (p, 10), (m, 16), (j, 19), (u, 21), (b, 25), (d, 27), (p, 28), (f, 28)]",
            "[(d, 5), (k, 5), (l, 6), (x, 10), (b, 16), (w, 17), (h, 18), (y, 23)]",
            "[(y, 2), (v, 4), (j, 9), (b, 12), (k, 23), (m, 23), (v, 23), (o, 24), (j, 28), (y, 28), (u, 30), (i, 30)]",
            "[(s, 1), (i, 3), (d, 5), (y, 5), (i, 6), (x, 7), (y, 8), (j, 14), (c, 18), (q, 21), (z, 25)]",
            "[(f, 2), (p, 7), (v, 9), (x, 13), (y, 15), (m, 18), (d, 18), (w, 20), (a, 20), (v, 21), (e, 27), (n, 28)]",
            "[(s, 3), (p, 3), (q, 3), (m, 7), (u, 8), (z, 11), (l, 13), (n, 14), (v, 14), (i, 15), (q, 16), (m, 24), (f, 26)]",
            "[(u, 2), (i, 2), (q, 3), (u, 4), (u, 10), (r, 16), (m, 19), (w, 21), (x, 22), (e, 29)]",
            "[(u, 4), (s, 8), (r, 18), (b, 21), (o, 22), (v, 22), (f, 22), (o, 23), (e, 23), (q, 25), (k, 27), (z, 28), (j, 29)]",
            "[(m, 3), (r, 5), (q, 8), (o, 9), (s, 9), (s, 10), (h, 11), (y, 12), (w, 16), (t, 16), (g, 17), (u, 21), (r, 29)]",
            "[(j, 5), (e, 6), (g, 9), (f, 10), (v, 12), (s, 15), (j, 18), (e, 20), (f, 22), (o, 22)]"};
    int[][] removesP = {{1, 5, 12, 13, 17, 18, 19, 19},
            {1, 10, 13},
            {11, 14, 15, 14, 25, 25},
            {4},
            {3},
            {4, 4, 4, 8},
            {1, 2, 1, 5},
            {1, 0},
            {3, 3, 5, 4, 12, 12},
            {3},
            {5, 4, 4, 7},
            {2, 3, 7, 9, 19, 18, 17, 17},
            {1, 2, 3, 2, 2, 2, 2, 7, 10, 12},
            {2, 6, 7},
            {3},
            {2, 1, 1, 1, 6, 11, 13, 14},
            {4, 7},
            {3, 4, 6, 6, 5, 5, 5, 5, 8, 7, 7, 10, 17},
            {5, 5}};
    String[][] removesV = {{"q", "j", "u", "t", "n", "x", "q", "w"},
            {"g", "q", "w"},
            {"i", "z", "n", "w", "q", "j"},
            {"u"},
            {"d"},
            {"y", "b", "l", "z"},
            {"h", "u", "x", "x"},
            {"b", "t"},
            {"i", "k", "a", "m", "p", "j"},
            {"f"},
            {"d", "k", "l", "x"},
            {"y", "v", "j", "b", "k", "m", "v", "o"},
            {"s", "i", "d", "y", "i", "x", "y", "j", "c", "q"},
            {"f", "p", "v"},
            {"s"},
            {"u", "i", "q", "u", "u", "r", "m", "w"},
            {"u", "s"},
            {"m", "r", "q", "o", "s", "s", "h", "y", "w", "t", "g", "u", "r"},
            {"j", "e"}};
    String[] removeToStrings = {"[(u, 22)]",
            "[(r, 16), (g, 23), (j, 27)]",
            "[]",
            "[(p, 3), (s, 3), (f, 10), (v, 12), (j, 14), (l, 15), (s, 16), (s, 17), (r, 24), (y, 28)]",
            "[(a, 11), (t, 13), (x, 18), (j, 20), (z, 20), (x, 23), (a, 29)]",
            "[(s, 14), (q, 16), (z, 19), (q, 23), (l, 24)]",
            "[(n, 6), (v, 16), (z, 22)]",
            "[(n, 1), (z, 6), (z, 7), (y, 9), (r, 11), (o, 18), (p, 18), (t, 21), (q, 21), (s, 26), (d, 27)]",
            "[(k, 13), (s, 14), (e, 18), (i, 20), (l, 21), (u, 24)]",
            "[(y, 3), (y, 3), (b, 6), (p, 9), (m, 15), (j, 18), (u, 20), (b, 24), (d, 26), (p, 27), (f, 27)]",
            "[(b, 12), (w, 13), (h, 14), (y, 19)]",
            "[(j, 20), (y, 20), (u, 22), (i, 22)]",
            "[(z, 15)]",
            "[(x, 10), (y, 12), (m, 15), (d, 15), (w, 17), (a, 17), (v, 18), (e, 24), (n, 25)]",
            "[(p, 2), (q, 2), (m, 6), (u, 7), (z, 10), (l, 12), (n, 13), (v, 13), (i, 14), (q, 15), (m, 23), (f, 25)]",
            "[(x, 14), (e, 21)]",
            "[(r, 16), (b, 19), (o, 20), (v, 20), (f, 20), (o, 21), (e, 21), (q, 23), (k, 25), (z, 26), (j, 27)]",
            "[]",
            "[(g, 7), (f, 8), (v, 10), (s, 13), (j, 16), (e, 18), (f, 20), (o, 20)]"};

    public String generateClassName(String name) {
        if (getClass().toString().contains(".")) {
            return getClass().toString().substring(6, getClass().toString().lastIndexOf(".") + 1) + name;
        }
        return name;
    }

    public ArrayList<String> allowedImports = new ArrayList<>();

    @Before
    public void setup()
    {
        allowedImports.add("java.util.ArrayList");
    }

    @Test(timeout = 250)
    public void checkImports() throws Exception{
        String className = "DS6_NoStarvePriorityQueue";
        String fileName = "src/"+generateClassName(className).replaceAll("\\.","/")+".java";
        boolean allowedOnly = true;
        ArrayList<String> invalidImport = new ArrayList<>();
        try
        {

            File file = new File(fileName);
            Scanner fromFile = new Scanner(file);
            while(fromFile.hasNextLine())
            {
                String line = fromFile.nextLine().trim();
                if(line.contains("import"))
                {
                    boolean good = false;
                    for(String allowed: allowedImports)
                    {
                        if(line.matches("\\s*import\\s+"+allowed+"\\s*;\s*(//\\.*)?"))
                            good=true;
                    }
                    if(!good)
                    {
                        allowedOnly=false;
                        invalidImport.add(line);
                    }
                }

            }
        }
        catch(Exception e)
        {
            Assert.assertTrue("Missing File: "+className+".java",false);
            allowedOnly = false;
        }

        Assert.assertTrue("Invalid imports: "+invalidImport,allowedOnly);
    }
    @Test(timeout = 250)
    public void test1() throws Exception {
        try
        {
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_NoStarvePriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");

            Object check =  priorityQueue.getConstructor().newInstance();

            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
            Assert.assertNull(poll.invoke(check));
            Assert.assertNull(element.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception {
        try
        {
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_NoStarvePriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");

            Object check =  priorityQueue.getConstructor().newInstance();

            offer.invoke(check,new DS6_PriorityNode<>(1,1));
            offer.invoke(check,new DS6_PriorityNode<>(0,0));
            offer.invoke(check,new DS6_PriorityNode<>(2,2));

            Assert.assertFalse((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(3,((Integer)size.invoke(check)).intValue());
            Assert.assertEquals(0,((DS6_PriorityNode<Integer>)get.invoke(check,0)).getPriority().intValue());
            Assert.assertEquals(0,((DS6_PriorityNode<Integer>)get.invoke(check,0)).getData().intValue());
            Assert.assertEquals(1,((DS6_PriorityNode<Integer>)get.invoke(check,1)).getPriority().intValue());
            Assert.assertEquals(1,((DS6_PriorityNode<Integer>)get.invoke(check,1)).getData().intValue());
            Assert.assertEquals(2,((DS6_PriorityNode<Integer>)get.invoke(check,2)).getPriority().intValue());
            Assert.assertEquals(2,((DS6_PriorityNode<Integer>)get.invoke(check,2)).getData().intValue());

            DS6_PriorityNode<Integer> node = ((DS6_PriorityNode<Integer>)element.invoke(check));
            Assert.assertEquals(0,node.getPriority().intValue());
            Assert.assertEquals(0,node.getData().intValue());
            node = ((DS6_PriorityNode<Integer>)poll.invoke(check));
            Assert.assertEquals(0,node.getPriority().intValue());
            Assert.assertEquals(0,node.getData().intValue());

            node = ((DS6_PriorityNode<Integer>)element.invoke(check));
            Assert.assertEquals(0,node.getPriority().intValue());
            Assert.assertEquals(1,node.getData().intValue());
            node = ((DS6_PriorityNode<Integer>)poll.invoke(check));
            Assert.assertEquals(0,node.getPriority().intValue());
            Assert.assertEquals(1,node.getData().intValue());

            clear.invoke(check);
            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
            Assert.assertNull(poll.invoke(check));

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test3() throws Exception {
        try
        {
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_NoStarvePriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");
            Method toString = priorityQueue.getMethod("toString");

            for(int x=0; x<19; x++)
            {
                Object check =  priorityQueue.getConstructor().newInstance();

                for(int a=0; a<addsP[x].length; a++)
                    offer.invoke(check,new DS6_PriorityNode<String>(addsV[x][a],addsP[x][a]));

                Assert.assertFalse((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(addsP[x].length,((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(toStrings[x],toString.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test5() throws Exception {
        try
        {
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_NoStarvePriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");
            Method toString = priorityQueue.getMethod("toString");

            for(int x=0; x<19; x++)
            {
                Object check =  priorityQueue.getConstructor().newInstance();

                for(int a=0; a<addsP[x].length; a++)
                    offer.invoke(check,new DS6_PriorityNode<String>(addsV[x][a],addsP[x][a]));

                Assert.assertFalse((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(addsP[x].length,((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(toStrings[x],toString.invoke(check));

                for(int a=0; a<removesP[x].length; a++) {
                    DS6_PriorityNode<String> node = ((DS6_PriorityNode<String>)element.invoke(check));
                    Assert.assertEquals(removesP[x][a],node.getPriority().intValue());
                    Assert.assertEquals(removesV[x][a],node.getData().toString());

                    node = ((DS6_PriorityNode<String>)poll.invoke(check));
                    Assert.assertEquals(removesP[x][a],node.getPriority().intValue());
                    Assert.assertEquals(removesV[x][a],node.getData().toString());
                }

                Assert.assertEquals(removeToStrings[x],toString.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test4() throws Exception {
        try
        {
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_NoStarvePriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");
            Method toString = priorityQueue.getMethod("toString");

            for(int x=0; x<19; x++)
            {
                Object check =  priorityQueue.getConstructor().newInstance();

                for(int a=0; a<addsP[x].length; a++)
                    offer.invoke(check,new DS6_PriorityNode<String>(addsV[x][a],addsP[x][a]));

                clear.invoke(check);

                Assert.assertTrue((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
                Assert.assertNull(element.invoke(check));
                Assert.assertNull(poll.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception {
        try
        {
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_MyPriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");
            Method toString = priorityQueue.getMethod("toString");

            for(int x=0; x<19; x++)
            {
                Object check =  priorityQueue.getConstructor().newInstance();


                for(int a=0; a<addsP[x].length; a++)
                    offer.invoke(check,new DS6_PriorityNode<String>(addsV[x][a],addsP[x][a]));

                Assert.assertFalse((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(addsP[x].length,((Integer)size.invoke(check)).intValue());

            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
