package com.artalgame.life;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CellContainer {
   private byte[] cells;
   private int rowCount,columnCount;
   private int oldRowCount, oldColumnCount;
   private MessageDigest digest;
   private StringBuffer hash;
   
   public CellContainer(int rowCount, int columnCount) throws NoSuchAlgorithmException{
	   updateSize(rowCount, columnCount);
   }
   
   public void updateSize(int rowCount,int columnCount) throws NoSuchAlgorithmException{
	   updateOldValue();
	   setRowCount(rowCount);
	   setColumnCount(columnCount);
	   updateContainer();
	   updateHash();
   }
   
   public void setElement(int row, int column, byte value) throws NoSuchAlgorithmException{
	   cells[row*rowCount+column] = value;
	   updateHash();
   }
   
   public byte getElement(int row, int column){
	   row = checkRow(row);
	   column = checkColumn(column);
	   return cells[row*rowCount+column];
   }   
   
   public void swapElement(int row, int column) throws NoSuchAlgorithmException{
	   setElement(row, column,(byte)(getElement(row, column)^1));
   }
   
   public String getHash(){
	  
       return hash.toString();
   }
   
   private MessageDigest createDigest() throws NoSuchAlgorithmException{
	   return MessageDigest.getInstance("SHA-512");
   }
   
   private void updateHash() throws NoSuchAlgorithmException{
	   if (digest == null){
		   digest = createDigest();
	   }
	   digest.update(cells);
	   byte[] code = digest.digest();
	   hash = new StringBuffer();
	   for(int i=0;i<code.length;i++){
		   hash.append(Integer.toHexString(0xFF & code[i]));
	   }
   }
   
   private byte getOldElement(int row,int column){
	   row = checkRow(row);
	   column = checkColumn(column);
	   return cells[row*oldRowCount+column];
   }
   private int  checkRow(int row){
	   if(row == -1){
		   return rowCount-1;
	   }
	   if(row == rowCount){
		   return 0;
	   }
	   return row;
   }
      
   private int  checkColumn(int column){
	   if(column == -1){
		   return columnCount-1;
	   }
	   if(column == columnCount){
		   return 0;
	   }
	   return column;
   }   
   
   private void updateOldValue(){
	   oldColumnCount = columnCount;
	   oldRowCount = rowCount;
   }
   
   private void updateContainer(){
	   if(cells == null){
		   cells = createContainer();
	   }
	   else{
		   copyToNew();
	   }
   }
   
   private void copyToNew(){
	   int n = Math.min(oldRowCount, rowCount);
	   int m = Math.min(oldColumnCount, columnCount);
	   byte[] newCells = createContainer();
	   copyElements(n,m,newCells);
	   cells= newCells;
   }
   
   private void copyElements(int minRowCount,int minColummnCount,byte[] newCells){
	   for(int i=0;i<minRowCount;i++)
	       for(int j=0;j<minColummnCount;j++){
	    	   newCells[i*minRowCount+j] = getOldElement(i,j); 
	       }
   }
   
   
   private byte[] createContainer(){
	   return new byte[rowCount*columnCount];
   }
   
   private void setRowCount(int rowCount){
	   this.rowCount = rowCount;
   }
   
   private void setColumnCount(int columnCount){
	   this.columnCount = columnCount;
   }
}
