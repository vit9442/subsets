import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

    /*
    &  - побитовое логическое "И"
    |  - побитовое логическое "ИЛИ"
    << - битовый сдвиг влево
    >> - битовый сдвиг вправо
     */
    public static void main(String[] args) {

        List<Item> items = Arrays.asList(
                new Item(356.4, 10),
                new Item(13.75, 40),
                new Item(136.63, 70),
                new Item(13, 300),
                new Item(130, 200)
        );
        double maxWeight = 300.0;
       // System.out.println("Max cost : " + findMaxCost(items, maxWeight));

        /*	Задание 1.public static List<Item> findBestSetOfItems(double maxWeight) – метод должен возвращать список (используйте arrayList) объектов класса Item,
            суммарная стоимость которых максимальна, при этом не превышает maxWeight. */

        System.out.println("Задание 1.Результат работы метода findBestSetOfItems " + "\nсписок объектов Item c максимальной стоймостью и весом не более " + maxWeight);
        List<Item> bestItems = findBestSetOfItems(maxWeight, items);
        for (int i = 0; i < bestItems.size(); i++)
            System.out.println("cost " + bestItems.get(i).getCost() + " weight " + bestItems.get(i).getWeight());

       /*   Задание 2. public static List<List<Item>> getAllSubsets(List<Item> items) – метод возвращает всевозможные комбинации наших вещей в виде списка списков.
            Обратите внимание: List<List<Item> означает список, который хранит списки, которые хранят Item. Т.е мы имеем что-то такое:*/
        System.out.println("\nЗадание 2.public static List<List<Item>> getAllSubsets(List<Item> items) – метод возвращает всевозможные комбинации наших вещей в виде списка списков:");
        System.out.println("Результат работы метода allItemsVar:");
        List<List<Item>> allItemsVar = getAllSubsets(items);
        for(int i = 0; i < allItemsVar.size(); i++){
           List<Item> item = allItemsVar.get(i);
            System.out.println("Список №:" + (i + 1));
            for(int j = 0; j < item.size(); j++) {
                System.out.print("Item " + (j+1) + ": Cost " + item.get(j).getCost());
                System.out.println(", weight " + item.get(j).getWeight());
            }
        }

    }

    //Возвращает true, если число является степенью двойки.
    public static boolean isPowerOfTwoNaive(int number) {
        while (number % 2 == 0) {
            number /= 2;
        }
        return number == 1;
    }

    public static boolean isPowerOfTwoOptimized(int number) {
        return (number & (number - 1)) == 0;
    }

    //Есть список вещей, есть ограничение по максимальному весу. Определить максимальную суммарную стоимость награбленного
    public static double findMaxCost(List<Item> items, double maxWeight) {
        double maxCost = 0.0;
        for (int mask = 0; mask < (1 << items.size()); ++mask) {
            //На данной итерации мы имеем какой-то набор вещей.
            double totalCost = 0;      //Суммарная стоимость
            double totalWeight = 0;    //Суммарный вес
            for (int index = 0; index < items.size(); ++index) {
                int value = (mask >> index) & 1;
                if (value == 1) {
                    totalCost += items.get(index).getCost();
                    totalWeight += items.get(index).getWeight();

                }
            }
            //Здесь мы будем иметь суммарную стоимость данного набора вещей и суммарный вес.'
            //Если мы можем поместить данный набор вещей в нашу сумку
            if (totalWeight <= maxWeight) {
                maxCost = Math.max(totalCost, maxCost);
                //То же самое, что и данный код ниже:
//                if(totalCost > maxCost) {
//                    maxCost = totalCost;
//                }
            }
        }
        return maxCost;
    }



 /*	Задание 1.public static List<Item> findBestSetOfItems(double maxWeight) – метод должен возвращать список (используйте arrayList) объектов класса Item,
            суммарная стоимость которых максимальна, при этом не превышает maxWeight. */
    public static List<Item> findBestSetOfItems(double maxWeight, List<Item> items) {

        double maxCost = 0.0;

        List<Item> resultItems = new ArrayList<Item>();
        for (int mask = 0; mask < (1 << items.size()); ++mask) {
            List<Item> temptItems = new ArrayList<Item>();
            //На данной итерации мы имеем какой-то набор вещей.
            double totalCost = 0;      //Суммарная стоимость
            double totalWeight = 0;    //Суммарный вес
            for (int index = 0; index < items.size(); ++index) {
                int value = (mask >> index) & 1;
                if (value == 1) {
                    totalCost += items.get(index).getCost();
                    totalWeight += items.get(index).getWeight();
                    temptItems.add(items.get(index));
                }
            }
            //Здесь мы будем иметь суммарную стоимость данного набора вещей и суммарный вес.'
            //Если мы можем поместить данный набор вещей в нашу сумку
            if (totalWeight <= maxWeight) {
                if (maxCost < totalCost) {
                    maxCost = totalCost;
                    resultItems = temptItems;
                }

            }

        }
        return resultItems;
    }

    /*   Задание 2. public static List<List<Item>> getAllSubsets(List<Item> items) – метод возвращает всевозможные комбинации наших вещей в виде списка списков.
         Обратите внимание: List<List<Item> означает список, который хранит списки, которые хранят Item. Т.е мы имеем что-то такое:*/
    public static List<List<Item>> getAllSubsets(List<Item> items) {
        List<List<Item>> allItemsVar = new ArrayList<List<Item>>();
        for (int mask = 0; mask < (1 << items.size()); ++mask) {
            List<Item> item = new ArrayList<Item>();
            for (int index = 0; index < items.size(); ++index) {
                int value = (mask >> index) & 1;
                if (value == 1) {
                    item.add(items.get(index));
                }
            }
            if (item.size() > 0)
                allItemsVar.add(item);
        }
        return allItemsVar;
    }

}
