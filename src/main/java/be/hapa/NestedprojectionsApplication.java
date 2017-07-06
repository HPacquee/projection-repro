package be.hapa;

import be.hapa.domain.Department;
import be.hapa.domain.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.*;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@SpringBootApplication
public class NestedprojectionsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(NestedprojectionsApplication.class);

	@Autowired
	private DepartmentRepository departmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(NestedprojectionsApplication.class, args);
	}

	@PostConstruct
	public void insertSomeData() {
		Department parent = new Department("parent");
		Department child1 = new Department("child1");
		Department child2 = new Department("child2");

		parent.addChild(child1);
		parent.addChild(child2);
		departmentRepository.save(parent);
	}

	@Bean
	public ResourceProcessor<Resource<Department>> workplacePagedCollectionProcessor(final EntityLinks links) {
		return new ResourceProcessor<Resource<Department>>() {
			@Override
			public Resource<Department> process(Resource<Department> resource) {
				resource.getLinks().forEach(link -> LOGGER.info(resource.getContent().getName() + " : " + link.getRel() + " " + link.getHref() ));
				resource.add(new Link("https://start.spring.io", "startIt"));
				return resource;
			}
		};
	}
}
