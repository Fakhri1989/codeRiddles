package org.example.eulerproject.controllers;


import org.example.eulerproject.services.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping("/dynamicprogramming")
public class DynamicProgrammingController {

    @Autowired
    DynamicService dynamicService;

    @PostMapping("/blockcombinations1")
    public ResponseEntity<BigInteger> euler114(@RequestBody Map<String, Integer> body){
        return dynamicService.euler114(body.get("minBlockSize"),body.get("rowLength"));
    }

    @PostMapping("/blockcombinations2")
    public ResponseEntity<Integer> euler115(@RequestBody Map<String, Integer> body){
        return dynamicService.euler115(body.get("minBlockSize"),body.get("targetResults"));
    }

    @PostMapping("/redgreenbluelimited")
    public ResponseEntity<BigInteger> euler116(@RequestBody Map<String, Integer> body){
        return dynamicService.euler116(body.get("rowSize"),body.get("red"),body.get("blue"),body.get("green"));
    }

    @PostMapping("/redgreenblue")
    public ResponseEntity<BigInteger> euler117(@RequestBody Map<String, Integer> body){
        return dynamicService.euler117(body.get("rowSize"),body.get("red"),body.get("blue"),body.get("green"));
    }

    @PostMapping("/tilingdominos")
    public ResponseEntity<BigInteger> kattisTritiling(@RequestBody Map<String, Integer> body){
        return dynamicService.kattisTritiling(body.get("columns"));
    }

    @PostMapping("/domtrom")
    public ResponseEntity<Integer> dominosTrominos(@RequestBody Map<String, Integer> body){
        return dynamicService.dominosTrominos(body.get("columns"));
    }

}
