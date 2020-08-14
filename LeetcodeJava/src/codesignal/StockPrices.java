package codesignal;

import java.util.stream.IntStream;

//两个长度相等的lists, prices 和 algo; 一个 integer k.
// prices 是每天股票的价格. algo 用 0/1表示, 是每天进行的操作 以当天的价格进行 buy (0) 或者 sell (1).
// revenue 的算法是 sell 的价格的和减去 buy 的价格的和.
// 然后在 algo 中选取一个长度为 k 的区间, 把所有的操作都变成 sell (1),
// algo 的其它位置的操作不变. 输出最大能得到的 revenue.
// e.g. prices = [2, 1, 4, 5, 6, 7, 8], algo = [0, 1, 0, 0, 0, 1, 0], k = 4.
// 如果把 algo 最后的 4 个操作变成 sell, 那么 algo = [0, 1, 0, 1, 1, 1, 1],
// 此时的 revenue 是 -2+1-4+5+6+7+8=21 是能达到的最大的 revenue, 所以输出21.

public class StockPrices {
    // finalReturn = original + rises[i]
    // for rises start from index 0:
    // [i]'s val = + 2 * prices[i] for algo[i] = 0
    //           = + 0             for algo[i] = 1
    // if rises are not start from 0:
    //  rises[i] = rises[i - 1] - [i-1]'s val + [i+k-1]'s val

    public int maxPriceReturn(int[] prices, int[] algo, int k){
        int[] rises = new int[prices.length - k + 1];
        for(int i = 0; i <= prices.length - k ; i++){
            if (i == 0) {
                int rise = 0;
                for(int j = 0; j < k; j++){
                    int increase = algo[i + j] == 0 ? 2 * prices[i + j] : 0;
                    rise += increase;
                }
                rises[i] = rise;
            } else {
                int decrease = algo[i - 1] == 0 ? 2 * prices[i - 1] : 0;
                int increase = algo[i + k - 1] == 0 ? 2 * prices[i + k - 1] : 0;
                rises[i] = rises[i - 1] - decrease + increase;
            }
        }

        int oriReturn = 0;
        for(int i = 0; i < prices.length; i++){
            oriReturn += algo[i] == 0 ? -prices[i] : prices[i];
        }
        int maxRise = IntStream.of(rises).max().getAsInt();

        return oriReturn + maxRise;
    }

    public static void main(String[] args) {
        int[] prices = {2, 1, 4, 5, 6, 7, 8},
                algo = {0, 1, 0, 0, 0, 1, 0};
        int k = 4;
        StockPrices stockPrices = new StockPrices();
        int rst = stockPrices.maxPriceReturn(prices, algo, k);
        System.out.println(rst);
    }
}
