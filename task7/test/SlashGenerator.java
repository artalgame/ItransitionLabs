class SlashGenerator{
	public static void main(String[] args){
	    int i=3;
		while(i<100000)
		{
			System.out.print(i);
		    for(int j=0;j<i;j++)
			{
				System.out.print("\\");
			}
			System.out.println();
			i*=2;
			i++;
		}
	}
}