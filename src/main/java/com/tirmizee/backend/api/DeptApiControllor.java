package com.tirmizee.backend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.dao.DeptDao;
import com.tirmizee.backend.dto.DeptDTO;
import com.tirmizee.backend.dto.Response;
import com.tirmizee.core.entities.DemoDept;
import com.tirmizee.core.mapper.DeptMapper;
import com.tirmizee.core.mapper.PageMapper;
import com.tirmizee.core.repositories.DeptRepository;

@RestController
@RequestMapping(path = "/dept")
public class DeptApiControllor {

	@Autowired
	private PageMapper mapper;
	
	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private DeptRepository deptRepository;
	
	@ExceptionHandler(value = Exception.class)
	public Response<?> handleException(Exception ex){
		Response<DeptDTO> response = new Response<>();
		response.setMsgCode("ERROR000");
		response.setMsgName(ex.getMessage());
		return response;
	}
	
	@GetMapping(value = "/find/all")
	public List<DeptDTO> findAll() {
		return DeptMapper.INSTANCE.toListDTO(deptRepository.all());
	}
	
	@GetMapping(value = "/find/sort")
	public List<DeptDTO> findAndSort() {
		List<DemoDept> entities = deptRepository.findAll(new Sort(Direction.DESC, "loc"));
		return DeptMapper.INSTANCE.toListDTO(entities);
	}
	
	@GetMapping(value = "/findone/{id}")
	public DemoDept findone(@PathVariable Integer id) {
		return deptRepository.findOne(id);
	}
	
	@GetMapping(value = "/getone/{id}")
	public DemoDept getone(@PathVariable Integer id) {
		return deptRepository.getOne(id);
	}
	
	@PostMapping(value = "/create")
	public Response<DeptDTO> create(@RequestBody DeptDTO deptDTO) {
		Response<DeptDTO> response = new Response<>();
		DemoDept dept = DeptMapper.INSTANCE.toEntity(deptDTO);
		deptRepository.save(dept);
		response.setMsgCode("200");
		response.setMsgName("Success");
		response.setDetail(deptDTO);
		return response;
	}
	
	@PostMapping(value = "/update")
	public Response<DeptDTO> update(@RequestBody DeptDTO deptDTO) {
		Response<DeptDTO> response = new Response<>();
		DemoDept entity = deptRepository.findOne(deptDTO.getDeptno());
		entity.setDname(deptDTO.getDname());
		entity.setLoc(deptDTO.getLoc());
		deptRepository.save(entity);
		response.setMsgCode("200");
		response.setMsgName("Success");
		response.setDetail(deptDTO);
		return response;
	}
	
	@GetMapping(value = "/delete/{deptno}")
	public Response<Object> delete(@PathVariable Integer deptno) {
		Response<Object> response = new Response<>();
		deptRepository.delete(deptno);
		response.setMsgCode("200");
		response.setMsgName("Success");
		return response;
	}
	
	@GetMapping(value = "/dname/{dname}")
	public List<DeptDTO> dname(@PathVariable String dname){
		List<DemoDept> entities = deptRepository.findByName(dname);
		return DeptMapper.INSTANCE.toListDTO(entities);
	}
	
	@GetMapping(value = "/loc/{loc}")
	public List<DeptDTO> all(@PathVariable String loc){
		List<DemoDept> entities = deptRepository.findByLoc(loc);
		return DeptMapper.INSTANCE.toListDTO(entities);
	}
	
	@GetMapping(value = "/page")
	public Page<DeptDTO> page(){
//		Sort sort = new Sort(Sort.Direction.ASC, "deptno");
		Pageable pageable = new PageRequest(0, 2);
		Page<DemoDept> entities = deptRepository.findNativeWithPagination(pageable);
		return mapper.map(entities, DeptDTO.class);
	}
	
	@PostMapping(value = "/dao/criteria")
	public List<DeptDTO> dao(@RequestBody DeptDTO deptDTO){
		List<DemoDept> entities = deptDao.findByCriteriaQuery(deptDTO);
		return DeptMapper.INSTANCE.toListDTO(entities);
	}
	
}
