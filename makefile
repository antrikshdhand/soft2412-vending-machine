run:
	gradle run

test:
	gradle test

clean:
	gradle clean
	rm app/transactions.csv

clean_run:
	gradle clean
	rm app/transactions.csv
	gradle run

full:
	gradle clean
	rm app/transactions.csv
	gradle test
	gradle run