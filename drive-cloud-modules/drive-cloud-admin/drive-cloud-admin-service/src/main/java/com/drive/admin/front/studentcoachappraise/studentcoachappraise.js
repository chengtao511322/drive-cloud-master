import request from '@/utils/request'


// 分页查询学员教练互评表列表
export function listPageStudentCoachAppraise(query) {
  return request({
    url: '/admin/studentCoachAppraise/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询学员教练互评表列表
export function listStudentCoachAppraise(query) {
  return request({
    url: '/admin/studentCoachAppraise/findList',
    method: 'get',
    params: query
  })
}

// 查询学员教练互评表详细
export function getStudentCoachAppraise(id) {
  return request({
    url: '/admin/studentCoachAppraise/' + id,
    method: 'get'
  })
}

// 新增学员教练互评表
export function addStudentCoachAppraise(data) {
  return request({
    url: '/admin/studentCoachAppraise',
    method: 'post',
    data: data
  })
}

// 修改学员教练互评表
export function updateStudentCoachAppraise(data) {
  return request({
    url: '/admin/studentCoachAppraise',
    method: 'put',
    data: data
  })
}

// 删除学员教练互评表
export function delStudentCoachAppraise(id) {
  return request({
    url: '/admin/studentCoachAppraise/' + id,
    method: 'delete'
  })
}


// 状态启用/停用学员教练互评表
export function changeStatus(data) {
  return request({
    url: '/admin/studentCoachAppraise/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学员教练互评表
export function exportStudentCoachAppraise(query) {
  return request({
    url: '/admin/studentCoachAppraise/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}