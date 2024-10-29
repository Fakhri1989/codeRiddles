package org.example.eulerproject.services;

import org.example.eulerproject.utils.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicService {

    @Autowired
    Calculator calculator;

    public ResponseEntity<BigInteger> euler114(Integer minBlockSize, Integer rowLength) {
        BigInteger result = calculator.dynamicBlockCombination(minBlockSize, rowLength);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<Integer> euler115(Integer minBlockSize, Integer targetResults) {
        BigInteger iterationResult = BigInteger.ZERO;
        Integer result = minBlockSize.intValue() - 1;

        while(iterationResult.intValue() < targetResults){
            result++;
            iterationResult = calculator.dynamicBlockCombination(minBlockSize, result);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<BigInteger> euler116(Integer rowSize, Integer red, Integer blue, Integer green) {
        BigInteger result = BigInteger.ZERO;

        result = result.add(calculator.simpleBlockCombination(red, rowSize));
        result =  result.add(calculator.simpleBlockCombination(green, rowSize));
        result =  result.add(calculator.simpleBlockCombination(blue, rowSize));

        result = result.subtract(new BigInteger("3"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<BigInteger> euler117(Integer rowSize, Integer red, Integer blue, Integer green) {

        BigInteger result = BigInteger.ZERO;

        List<Integer> blockSizes = new ArrayList<>();
        blockSizes.add(1);
        blockSizes.add(red);
        blockSizes.add(blue);
        blockSizes.add(green);

        result = result.add(calculator.differentBlockSizesCombinations(blockSizes, rowSize));


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<BigInteger> kattisTritiling(Integer columns) {
        if(columns % 2 == 1)
            return new ResponseEntity<>(BigInteger.ZERO, HttpStatus.NOT_FOUND);

        int rows = 3;

        List<List<Integer>> grid = new ArrayList<>(rows);

        // Create empty lists for each row
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                row.add(0);
            }
            grid.add(row);
        }


        BigInteger combos = calculator.tritiling(grid);

        return new ResponseEntity<>(combos, HttpStatus.OK);
    }

    public ResponseEntity<Integer> dominosTrominos(Integer columns) {

        Integer result = calculator.dominosTrominosWithTwoLines(columns, 0, true, true);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
