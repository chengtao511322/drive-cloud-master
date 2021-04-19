import request from '@/utils/request'

                
// 分页查询学员学车报名单列表
export function listPageStudentStudyProgressHistory(query) {
  return request({
    url: '/admin/studentStudyProgressHistory/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询学员学车报名单列表
export function listStudentStudyProgressHistory(query) {
  return request({
    url: '/admin/studentStudyProgressHistory/findList',
    method: 'post',
    data: query
  })
}

// 查询学员学车报名单详细
export function getStudentStudyProgressHistory(id) {
  return request({
    url: '/admin/studentStudyProgressHistory/' + id,
    method: 'get'
  })
}

// 新增学员学车报名单
export function addStudentStudyProgressHistory(data) {
  return request({
    url: '/admin/studentStudyProgressHistory',
    method: 'post',
    data: data
  })
}

// 修改学员学车报名单
export function updateStudentStudyProgressHistory(data) {
  return request({
    url: '/admin/studentStudyProgressHistory',
    method: 'put',
    data: data
  })
}

// 删除学员学车报名单
export function delStudentStudyProgressHistory(id) {
  return request({
    url: '/admin/studentStudyProgressHistory/' + id,
    method: 'delete'
  })
}


// 状态启用/停用学员学车报名单
export function changeStatus(data) {
  return request({
    url: '/admin/studentStudyProgressHistory/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学员学车报名单
export function exportStudentStudyProgressHistory(query) {
  return request({
    url: '/admin/studentStudyProgressHistory/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}