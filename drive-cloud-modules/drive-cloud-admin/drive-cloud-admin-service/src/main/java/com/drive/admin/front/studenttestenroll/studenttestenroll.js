import request from '@/utils/request'

                                              
// 分页查询学员考试报名表列表
export function listPageStudentTestEnroll(query) {
  return request({
    url: '/admin/studentTestEnroll/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询学员考试报名表列表
export function listStudentTestEnroll(query) {
  return request({
    url: '/admin/studentTestEnroll/findList',
    method: 'post',
    params: query
  })
}

// 查询学员考试报名表详细
export function getStudentTestEnroll(testEnrollNo) {
  return request({
    url: '/admin/studentTestEnroll/' + testEnrollNo,
    method: 'get'
  })
}

// 新增学员考试报名表
export function addStudentTestEnroll(data) {
  return request({
    url: '/admin/studentTestEnroll',
    method: 'post',
    data: data
  })
}

// 修改学员考试报名表
export function updateStudentTestEnroll(data) {
  return request({
    url: '/admin/studentTestEnroll',
    method: 'put',
    data: data
  })
}

// 删除学员考试报名表
export function delStudentTestEnroll(testEnrollNo) {
  return request({
    url: '/admin/studentTestEnroll/' + testEnrollNo,
    method: 'delete'
  })
}


// 状态启用/停用学员考试报名表
export function changeStatus(data) {
  return request({
    url: '/admin/studentTestEnroll/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学员考试报名表
export function exportStudentTestEnroll(query) {
  return request({
    url: '/admin/studentTestEnroll/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}