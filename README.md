# Merchant's Guide to the Galaxy
Do you woant to be a Galaxy's merchant? I can help you that to complete the monetary conversion.

# Design thinking
According to the meaning of the question, the analysis of rules and texts will change, and they all have multiple processing methods, which are separated and use the chain of responsibility mode to achieve their internal decoupling. In the text parsing chain, each line of text will only have one parser, so it is automatically determined by the level according to the level. Each rule on the verification chain needs to be satisfied, so the parsed Roman characters are directly handed over to the parsing chain. After processing, it is considered that there will be repeated sequences. Therefore, a cache is created, and the cache is taken from the cache before verification.

# How to use?
- First, you should define the 'input.txt' file and it consists of line of text detailling your notes on the conversion between intergalactic units and roman numerals. For example, test input:
```$text
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?
```
will output:
```$text
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 780 Credits
I have no idea what you are talking about
```
- Then run the 'MerchantGuide.java' within package 'cn.dark.MerchantGuide' and you can specify the 'input.txt' path on command line else the application will conversion with default that it in the 'cn/dark/processor/input.txt' .
- Be attention, the text format is strict as above that the conversion doesn't run else. 
- The util must you provide useful information and input question correctly, you will see 'I have no idea what you are talking about' as above that you input the text incorrectly.

**Finally, good luck!**