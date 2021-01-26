import request from '@/utils/request'


// 查询学员信息表列表
export function listStudentInfo(query) {
  return request({
    url: '/member/studentInfo/pageList',
    method: 'get',
    params: query
  })
}

// 查询学员信息表详细
export function getStudentInfo(id) {
  return request({
    url: '/member/studentInfo/' + id,
    method: 'get'
  })
}

// 新增学员信息表
export function addStudentInfo(data) {
  return request({
    url: '/member/studentInfo',
    method: 'post',
    data: data
  })
}

// 修改学员信息表
export function updateStudentInfo(data) {
  return request({
    url: '/member/studentInfo',
    method: 'put',
    data: data
  })
}

// 删除学员信息表
export function delStudentInfo(id) {
  return request({
    url: '/member/studentInfo/' + id,
    method: 'delete'
  })
}

// 导出学员信息表
export function exportStudentInfo(query) {
  return request({
    url: '/member/studentInfo/exportXls',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}