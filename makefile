run:
	gradle run

test:
	gradle test

clean:
	gradle clean
	rm app/reports/transactions.csv

clean_run:
	gradle clean
	rm app/reports/transactions.csv
	gradle run

full:
	gradle clean
	rm app/reports/transactions.csv
	gradle test
	gradle run