//package Tests.Controllers;
//
//import com.example.socialmediaplanning.controller.EmployeeController;
//import com.example.socialmediaplanning.dao.EmployeeDao;
//import com.example.socialmediaplanning.entity.Employee;
//import com.example.socialmediaplanning.entity.Role;
//import com.example.socialmediaplanning.service.EmployeeService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//public class EmployeeControllerTests {
//    @Mock
//    private EmployeeService employeeService;
//
//    @InjectMocks
//    private EmployeeController employeeController;
//
//    private List<Employee> expectedEmployee;
//
//    @BeforeEach
//    public void BeforeEach() {
//        this.expectedEmployee = List.of(Employee.builder()
//                        .age("10")
//                        .appointments(null)
//                        .employeeName("Tudor")
//                        .company(null)
//                        .email("email_good")
//                        .id("3ce14511-8196-41d0-ba5a-f33571ee9b63")
//                        .role(Role.ADMIN)
//                        .build());
//    }
//
//    @AfterEach
//    public void afterEach() {
//        verifyNoMoreInteractions(employeeService);
//    }
//
//
////    @ParameterizedTest
////    @ValueSource(strings = {"G2NqpB", "3ce14511-8196-41d0-ba5a-f33571ee9b63", "value3"})
////    public void testGetEmployeeByID(String ID) {
////        Optional<Employee> actualEmployees = employeeController.findEmployee(ID);
////        assertEquals(expectedEmployee, actualEmployees.get());
////        verify(employeeController).findEmployee(ID);
////        verify(employeeService, times(1)).findEmployee(ID);
////
////
////    }
//
//
//}
