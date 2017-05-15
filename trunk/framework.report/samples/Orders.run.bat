cd..
runReport.bat -report-file samples/reports/Orders.report.xml -output-format "pdf" -connection "csv::samples/data/orders.csv->(firstRowHeader=true;rowDelimiter=#LF;encoding=utf-8)" -log true