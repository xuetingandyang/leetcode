package amazonOA;

import javax.lang.model.type.ArrayType;
import java.util.*;

// input:
// Map-items: (name, [relevance, price])
// sortParameter: 0 for name, 1 for relevance, 2 for price
// sortOrder: 0 for Ascending order, 1 for Descending order

// output: List<String>
// item names on the requested page in the order they are displayed

//Note: 0 < itemsPerPage <= min(20, numOfItems)
// EG: input:
//int numOfItems = 3, sortParameter = 1, sortOrder = 0
//items = [["item1", 10, 15], ["item2", 3, 4], ["item3", 17, 8]]
//int itemsPerPage = 2, pageNumber = 1
// --> output ["item3"]

// there are 3 items. -> sort by relevance (1) in ASC (0)
// -> item2, item1, item3
// Display up to 2 items per page
// -> page-0: item2, item1
// -> page-1: item3 (output)
public class SortWebsiteItemsinPage {
    public static List<String> sortWebsite(
            int numOfItems,
            Map<String, int[]> items,
            int sortParameter,
            int sortOrder,
            int itemsPerPage,
            int pageNumber
    ) {
        // itemName, relevance, price are actually EQUAL => convert into String[3]
        // convert map items => List<String[3]> itemsList -> then sort itemsList with custom comparator
        // Time: O(NlogN) for sort, N: # of items
        // Space: O(N)

        List<String[]> itemsList = new ArrayList<>();
        for (Map.Entry<String, int[]> item : items.entrySet()) {
            String[] itemCombine = new String[3];
            itemCombine[0] = item.getKey();
            itemCombine[1] = String.valueOf(item.getValue()[0]);
            itemCombine[2] = String.valueOf(item.getValue()[1]);

            itemsList.add(itemCombine);
        }

        Collections.sort(itemsList, (a, b) -> {
            switch (sortParameter) {
                case 0:
                    return sortOrder == 0 ? a[0].compareTo(b[0]) : b[0].compareTo(a[0]);
                case 1:
                    return sortOrder == 0 ? Integer.parseInt(a[1]) - Integer.parseInt(b[1]) : Integer.parseInt(b[1]) - Integer.parseInt(a[1]);
                case 2:
                    return sortOrder == 0 ? Integer.parseInt(a[2]) - Integer.parseInt(b[2]) : Integer.parseInt(b[2]) - Integer.parseInt(a[2]);
                default:
                    return 0;
            }
        });

        List<String> rst = new ArrayList<>();
        int start = pageNumber * itemsPerPage;
        int end = Math.min((pageNumber + 1) * itemsPerPage, numOfItems);

        for (int i = start; i < end; i ++) {
            rst.add(itemsList.get(i)[0]);
        }
        return rst;
    }

    public static void main(String[] args) {
        int numOfItems = 5, sortParameter = 0, sortOrder = 0, itemsPerPage = 3, pageNumber = 1;
        Map<String, int[]> items = new HashMap<>();
        items.put("item1", new int[]{10, 15});
        items.put("item2", new int[]{3, 4});
        items.put("item3", new int[]{17, 8});
        items.put("item4", new int[]{9, 6});
        items.put("item5", new int[]{11, 10});

        List<String> itemsOnPageNumber = sortWebsite(numOfItems, items, sortParameter, sortOrder, itemsPerPage, pageNumber);
        for (String s : itemsOnPageNumber) {
            System.out.println(s);
        }
    }
}
