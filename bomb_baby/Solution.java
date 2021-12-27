package foobar.bomb_baby;

import java.math.BigInteger;

public class Solution {

    static boolean impossible = false;
    
    public static boolean isSolvable(BigInteger x, BigInteger y)
    {
        if(x.compareTo(y) == 0){
            impossible = true;
            return false;
        } 
        
        if(x.compareTo(BigInteger.ZERO) <= 0 || y.compareTo(BigInteger.ZERO) <= 0) 
        {
            impossible = true;
            return false;
        }
        
        return true;
    }

    public static String solution(String x, String y) {

        BigInteger targetMach = new BigInteger(x); 
        BigInteger targetFacula = new BigInteger(y); 
        BigInteger generationsPassed = BigInteger.ZERO;

        while(isSolvable(targetMach, targetFacula)){
            if(targetFacula.compareTo(BigInteger.ONE) == 0 && targetMach.compareTo(BigInteger.ONE) > 0){
                generationsPassed = generationsPassed.add(targetMach.subtract(BigInteger.ONE));
                //System.out.println(generationsPassed.toString());
                break;
            }
            if(targetMach.compareTo(BigInteger.ONE) == 0 && targetFacula.compareTo(BigInteger.ONE) > 0){
                generationsPassed = generationsPassed.add(targetFacula.subtract(BigInteger.ONE));
                break;
            }
            

            if(targetFacula.compareTo(targetMach) > 0)
                while(targetFacula.compareTo(targetMach) > 0){
                    generationsPassed = generationsPassed.add(targetFacula.divideAndRemainder(targetMach)[0]) ;
                    targetFacula = targetFacula.divideAndRemainder(targetMach)[1] ;
                }

            else if(targetMach.compareTo(targetFacula) > 0)
                while(targetMach.compareTo(targetFacula) > 0){
                    generationsPassed = generationsPassed.add(targetMach.divideAndRemainder(targetFacula)[0]) ;
                    targetMach = targetMach.divideAndRemainder(targetFacula)[1] ;
                }
            else
                {impossible = true; break;}

    }

    if(impossible) 
        return "impossible";
    else
        return new String(generationsPassed.toString());
    }

    public static void main(String[] args) {
        System.out.println(solution(args[0], args[1]));
    }
}

