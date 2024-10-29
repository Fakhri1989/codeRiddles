package org.example.eulerproject.utils;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class Calculator {

    private static Map<Duple, BigInteger> changingBlockSizeCache = new HashMap<>();
    private static Map<Duple, BigInteger> simpleCache = new HashMap<>();
    private static Map<Integer, BigInteger> specificRowSizeCombinations = new HashMap<>();

    public BigInteger dynamicBlockCombination(Integer minBlockSize, Integer rowLength) {
        Duple key = new Duple();
        key.setMinSize(minBlockSize);
        key.setRowSize(rowLength);

        if (rowLength == 0)
            return new BigInteger("1");

        if (rowLength < minBlockSize)
            return new BigInteger("1");

        if (changingBlockSizeCache.containsKey(key))
            return changingBlockSizeCache.get(key);

        BigInteger result = BigInteger.ZERO;

        result = result.add(dynamicBlockCombination(minBlockSize, rowLength - 1));

        for (int i = minBlockSize; i <= rowLength; i++) {
            result = result.add(dynamicBlockCombination(minBlockSize, rowLength - i - 1));
        }

        changingBlockSizeCache.put(key, result);

        return result;
    }

    public BigInteger simpleBlockCombination(Integer blockSize, Integer rowLength) {
        Duple key = new Duple();
        key.setMinSize(blockSize);
        key.setRowSize(rowLength);

        if (rowLength < 0)
            return BigInteger.ZERO;

        if (rowLength == 0)
            return BigInteger.ONE;

        if (simpleCache.containsKey(key))
            return simpleCache.get(key);

        BigInteger result = BigInteger.ZERO;

        result = result.add(simpleBlockCombination(blockSize, rowLength - 1)).add(simpleBlockCombination(blockSize, rowLength - blockSize));


        simpleCache.put(key, result);

        return result;
    }

    public BigInteger differentBlockSizesCombinations(List<Integer> blockSizes, Integer rowLength) {
        if (rowLength < 0)
            return BigInteger.ZERO;

        if (rowLength == 0)
            return BigInteger.ONE;

        if (specificRowSizeCombinations.containsKey(rowLength))
            return specificRowSizeCombinations.get(rowLength);

        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < blockSizes.size(); i++) {

            BigInteger tempResult = BigInteger.ZERO;

            tempResult = tempResult.add(differentBlockSizesCombinations(blockSizes, rowLength - blockSizes.get(i)));

            result = result.add(tempResult);
        }

        specificRowSizeCombinations.put(rowLength, result);
        return result;
    }


    private static Set<List<List<Integer>>> resultGrid = new HashSet<>();

    public BigInteger tritiling(List<List<Integer>> grid) {

        if (!resultGrid.contains(grid) && !grid.stream().flatMap(row -> row.stream()).anyMatch(i -> i == null || i == 0)) {
            resultGrid.add(deepCopyGrid(grid));  // Add a copy of the grid
            return BigInteger.ONE;
        }

        BigInteger result = BigInteger.ZERO;

        outerLoop:
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size() - 1; j++) {
                if (grid.get(i).get(j) == 0 && grid.get(i).get(j + 1) == 0) {
                    grid.get(i).set(j, 1);
                    grid.get(i).set(j + 1, 1);
                    result = result.add(tritiling(grid));
                    grid.get(i).set(j, 0);
                    grid.get(i).set(j + 1, 0);
                    break outerLoop;
                }
            }
        }

        secondOuterLoop:
        for (int i = 0; i < grid.size() - 1; i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) == 0 && grid.get(i + 1).get(j) == 0) {
                    grid.get(i).set(j, 2);
                    grid.get(i + 1).set(j, 2);
                    result = result.add(tritiling(grid));
                    grid.get(i).set(j, 0);
                    grid.get(i + 1).set(j, 0);
                    break secondOuterLoop;
                }
            }
        }

        if (grid.stream().flatMap(row -> row.stream()).allMatch(i -> i == null || i == 0)) {
            resultGrid.clear();
        }

        return result;
    }


    private List<List<Integer>> deepCopyGrid(List<List<Integer>> original) {
        List<List<Integer>> copy = new ArrayList<>();
        for (List<Integer> row : original) {
            copy.add(new ArrayList<>(row));
        }
        return copy;
    }

    private Map<Duple, List<Integer>> dp = new HashMap<>();
    private final Integer MOD = 1000000007;

    public Integer dominosTrominosWithTwoLines(Integer columns, int i, boolean topSquareAvailable, boolean bottomSquareAvailable) {

        if (i == columns) {
            return 1;
        }

        Duple key = new Duple(columns, i);
        dp.computeIfAbsent(key, k -> new ArrayList<>());

        List<Integer> currentList = dp.get(key);

        while (currentList.size() < 4) {
            currentList.add(null);
        }

        int state = makeState(topSquareAvailable, bottomSquareAvailable);


        // Check if result for this state has already been calculated
        if (currentList.get(state) != null) {
            return currentList.get(state);
        }

        boolean nextColumn = i + 1 < columns;

        Integer result = 0;

        result += topSquareAvailable && bottomSquareAvailable && nextColumn ? dominosTrominosWithTwoLines(columns, i + 1, false, true) : 0;
        result += topSquareAvailable && bottomSquareAvailable && nextColumn ? dominosTrominosWithTwoLines(columns, i + 1, true, false) : 0;
        result += !topSquareAvailable && bottomSquareAvailable && nextColumn ? dominosTrominosWithTwoLines(columns, i + 1, true, false) : 0;
        result += !topSquareAvailable && bottomSquareAvailable && nextColumn ? dominosTrominosWithTwoLines(columns, i + 1, false, false) : 0;
        result += topSquareAvailable && !bottomSquareAvailable && nextColumn ? dominosTrominosWithTwoLines(columns, i + 1, false, false) : 0;
        result += topSquareAvailable && !bottomSquareAvailable && nextColumn ? dominosTrominosWithTwoLines(columns, i + 1, false, true) : 0;
        result += topSquareAvailable && bottomSquareAvailable ? dominosTrominosWithTwoLines(columns, i + 1, true, true) : 0;
        result += topSquareAvailable && bottomSquareAvailable && nextColumn ? dominosTrominosWithTwoLines(columns, i + 1, false, false) : 0;
        result += !topSquareAvailable && !bottomSquareAvailable ? dominosTrominosWithTwoLines(columns, i + 1, true, true) : 0;

        result %= MOD;

       currentList.set(state, result);

        return result;
    }

    private Integer makeState(boolean topSquareAvailable, boolean bottomSquareAvailable) {

        if(topSquareAvailable && bottomSquareAvailable)
            return 0;
        if(!topSquareAvailable && bottomSquareAvailable)
            return 1;
        if(topSquareAvailable && !bottomSquareAvailable)
            return 2;
        if(!topSquareAvailable && !bottomSquareAvailable)
            return 3;

        return 0;
    }
}
