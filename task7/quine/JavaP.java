class Java1{
    public static void main(String[] args){
	    char q=34;
		String[] s ={
		"================Sharp2==================",
		"using System;",
		"namespace Quine{",
		"   class Sharp2{",
		"	    public static void Main(string[] args){",
		"		    char q=(char)34;",
		"			String[] s = {",
		"			};",
		"			for(int i=76;i<=77;i++)",
		"				Console.WriteLine(s[i]);",
		"        for(int i=0;i<s.Length;i++)",
		"            Console.WriteLine(q+s[i]+q+',');",
		"        for(int i=78;i<=87;i++)",
		"            Console.WriteLine(s[i]);",
		"        }",
		"    }",
		"}",
		"================Sharp1==================",
		"using System;",
		"namespace Quine{",
		"   class Sharp1{",
		"	    public static void Main(string[] args){",
		"		    char q=(char)34;",
		"			String[] s = {",
		"			};",
		"			for(int i=1;i<=6;i++)",
		"				Console.WriteLine(s[i]);",
		"        for(int i=0;i<s.Length;i++)",
		"            Console.WriteLine(q+s[i]+q+',');",
		"        for(int i=7;i<=16;i++)",
		"            Console.WriteLine(s[i]);",
		"        }",
		"    }",
		"}",
		"================JAVA2===================",
		"class Java2{",
		"    public static void main(String[] args){",
		"        char q=34;",
		"        String[] s={",
		"        };",
		"        for(int i=18;i<=23;i++)",
		"            System.out.println(s[i]);",
		"        for(int i=0;i<s.length;i++)",
		"            System.out.println(q+s[i]+q+',');",
		"        for(int i=24;i<=33;i++)",
		"            System.out.println(s[i]);",
		"    }",
		"}",
		"================JAVA1===================",
		"class Java1{",
		"    public static void main(String[] args){",
		"        char q=34;",
		"        String[] s={",
		"        };",
		"        for(int i=35;i<=38;i++)",
		"            System.out.println(s[i]);",
		"        for(int i=0;i<s.length;i++)",
		"            System.out.println(q+s[i]+q+',');",
		"        for(int i=39;i<=47;i++)",
		"            System.out.println(s[i]);",
		"    }",
		"}",
		"================Ruby2===================",
		"q=34.chr;",
		"s=[",
		"];",
	    "s[49..52].each do |st|", 
	    "puts(st);",
		"end",
	    "s.each do |st|", 
	    "puts(q+st+q+',');",
		"end",
		"s[53..61].each do |st|", 
	    "puts(st);",
	    "end",
		"================Ruby1===================",
		"q=34.chr;",
		"s=[",
		"];",
		"s[63..64].each do |st|", 
	    "puts(st);",
		"end",
	    "s.each do |st|", 
	    "puts(q+st+q+',');",
		"end",
		"s[65..74].each do |st|", 
	    "puts(st);",
	    "end",
		};
		for(int i=35;i<=38;i++)
		    System.out.println(s[i]);
		for(int i=0;i<s.length;i++)
		    System.out.println(q+s[i]+q+',');
		for(int i=39;i<=47;i++)
		    System.out.println(s[i]);
	}
}