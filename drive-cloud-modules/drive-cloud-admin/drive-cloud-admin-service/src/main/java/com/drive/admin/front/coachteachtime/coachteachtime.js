import request from '@/utils/request'


// 分页查询教练课程时间表列表
export function listPageCoachTeachTime(query) {
  return request({
    url: '/admin/coachTeachTime/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询教练课程时间表列表
export function listCoachTeachTime(query) {
  return request({
    url: '/admin/coachTeachTime/findList',
    method: 'post',
    data: query
  })
}

// 查询教练课程时间表详细
export function getCoachTeachTime(id) {
  return request({
    url: '/admin/coachTeachTime/' + id,
    method: 'get'
  })
}

// 新增教练课程时间表
export function addCoachTeachTime(data) {
  return request({
    url: '/admin/coachTeachTime',
    method: 'post',
    data: data
  })
}

// 修改教练课程时间表
export function updateCoachTeachTime(data) {
  return request({
    url: '/admin/coachTeachTime',
    method: 'put',
    data: data
  })
}

// 删除教练课程时间表
export function delCoachTeachTime(id) {
  return request({
    url: '/admin/coachTeachTime/' + id,
    method: 'delete'
  })
}


// 状态启用/停用教练课程时间表
export function changeStatus(data) {
  return request({
    url: '/admin/coachTeachTime/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出教练课程时间表
export function exportCoachTeachTime(query) {
  return request({
    url: '/admin/coachTeachTime/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}