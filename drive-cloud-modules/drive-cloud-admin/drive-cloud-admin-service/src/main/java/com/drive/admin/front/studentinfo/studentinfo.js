import request from '@/utils/request'


// 分页查询学员信息表列表
export function listPageStudentInfo(query) {
  return request({
    url: '/admin/studentInfo/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询学员信息表列表
export function listStudentInfo(query) {
  return request({
    url: '/admin/studentInfo/findList',
    method: 'post',
    params: query
  })
}

// 查询学员信息表详细
export function getStudentInfo(id) {
  return request({
    url: '/admin/studentInfo/' + id,
    method: 'get'
  })
}

// 新增学员信息表
export function addStudentInfo(data) {
  return request({
    url: '/admin/studentInfo',
    method: 'post',
    data: data
  })
}

// 修改学员信息表
export function updateStudentInfo(data) {
  return request({
    url: '/admin/studentInfo',
    method: 'put',
    data: data
  })
}

// 删除学员信息表
export function delStudentInfo(id) {
  return request({
    url: '/admin/studentInfo/' + id,
    method: 'delete'
  })
}


// 状态启用/停用学员信息表
export function changeStatus(data) {
  return request({
    url: '/admin/studentInfo/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学员信息表
export function exportStudentInfo(query) {
  return request({
    url: '/admin/studentInfo/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}