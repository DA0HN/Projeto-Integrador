select seller.*, Department.Name as DepName
from seller inner join department
on seller.DepartmentId = Department.Id
where seller.Name = 'greg';

show tables;
select * from department;
select * from seller;

select seller.*, department.Name as depname 
from seller, department 
where seller.Name = 'greg' 
	and seller.DepartmentId = department.Id; 