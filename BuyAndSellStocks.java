public class BuyAndSellStocks {

    public int maxProfit(int[] stockPrices) {
        int buy = 0, sell = 1;
        int max = Integer.MIN_VALUE;

        while(sell < stockPrices.length) {

            if(stockPrices[buy] < stockPrices[sell])
                max = Math.max(max, stockPrices[sell] - stockPrices[buy]);
            else
                buy = sell;

            sell++;
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
